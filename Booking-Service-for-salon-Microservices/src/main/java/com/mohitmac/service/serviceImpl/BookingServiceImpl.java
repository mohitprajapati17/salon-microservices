package main.java.com.mohitmac.service.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.mohitmac.domain.BookingStatus;
import main.java.com.mohitmac.model.Booking;
import main.java.com.mohitmac.model.SalonReport;
import main.java.com.mohitmac.payload_DTO.BookingRequest;
import main.java.com.mohitmac.payload_DTO.SalonDTO;
import main.java.com.mohitmac.payload_DTO.ServiceOfferingDTO;
import main.java.com.mohitmac.payload_DTO.UserDTO;
import main.java.com.mohitmac.repository.BookingServiceRepository;
import main.java.com.mohitmac.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
    

    @Autowired
    private BookingServiceRepository bookingServiceRepository;
    @Override
    public Booking createBooking(BookingRequest bookingRequest,UserDTO userDTO,SalonDTO salonDTO,Set<ServiceOfferingDTO> setserviceOfferingDTO) {
        
        int totalDuration =setserviceOfferingDTO.stream().mapToInt(ServiceOfferingDTO::getDuration).sum();
        LocalDateTime BookingStartTime=bookingRequest.getStartTime();
        LocalDateTime BookingEndTime=BookingStartTime.plusMinutes(totalDuration);

        Boolean isTimeSlotAvailable=isTimeSlotAvailable(salonDTO,BookingStartTime,BookingEndTime);

        int totalPrice =setserviceOfferingDTO.stream().mapToInt(ServiceOfferingDTO::getPrice).sum();

        Set<Long> idlist=setserviceOfferingDTO.stream().map(ServiceOfferingDTO::getId).collect(Collectors.toSet());
        Booking booking=new Booking();
        booking.setCustomerId(userDTO.getId());
        booking.setSalonId(salonDTO.getId());
        booking.setStartTime(BookingStartTime);
        booking.setEndTime(BookingEndTime);
        booking.setServiceIds(idlist);
        booking.setStatus(BookingStatus.PENDING);
        booking.setTotalPrice(totalPrice);
        return bookingServiceRepository.save(booking);



        
    }   


    public Boolean isTimeSlotAvailable(SalonDTO salonDTO,LocalDateTime startTime,LocalDateTime endTime){
        List<Booking> existingBooking=getBookingsBySalonId(salonDTO.getId());
        LocalDateTime   salonOpenTime=salonDTO.getOpenTime().atDate(startTime.toLocalDate());
        LocalDateTime   salonCloseTime=salonDTO.getCloseTime().atDate(startTime.toLocalDate());
        if(startTime.isBefore(salonOpenTime) || endTime.isAfter(salonCloseTime)){
            throw new RuntimeException("Booking Time must be within salon's working  hours");
        }

        for(Booking booking:existingBooking){
            if(startTime.isBefore(booking.getEndTime()) && endTime.isAfter(booking.getStartTime())){
                throw new RuntimeException("Time slot is not available");
            }

            if(startTime.isEqual(booking.getStartTime()) || endTime.isEqual(booking.getEndTime())){
                throw new RuntimeException("Time slot is not available");
            }
        }
        return true;
    }
        
    

    @Override
    public List<Booking> getBookingsByCustomerId(Long customerId) {
        return bookingServiceRepository.findByCustomerId(customerId);
    }

    @Override
    public Booking getBookingById(Long bookingId)  {
        return bookingServiceRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public List<Booking> getBookingsBySalonId(Long salonId) {
        return bookingServiceRepository.findBySalonId(salonId);
    }

    @Override
    public Booking updateBooking(Long bookingId,BookingStatus status) {
        Booking booking=getBookingById(bookingId);
        booking.setStatus(status);
        return bookingServiceRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date,Long salonId) {
        List<Booking> bookings=getBookingsBySalonId(salonId);
        if(date==null){
            return  bookingServiceRepository.findAll();
        }
        return  bookings.stream().filter(booking->isSameDate(booking.getStartTime(),date)||isSameDate(booking.getEndTime(),date)).collect(Collectors.toList());
    }

    private Boolean isSameDate(LocalDateTime dateTime,LocalDate date){
        return dateTime.toLocalDate().equals(date);
    }
        

    @Override
    public SalonReport getSalonReport(Long salonId) {
        List<Booking> bookings=getBookingsBySalonId(salonId);
        int  totalBookings=bookings.size();
        Double totalEarning=bookings.stream().mapToDouble(Booking::getTotalPrice).sum();
        List<Booking>cancelledBookings=bookings.stream().filter(booking->booking.getStatus().equals(BookingStatus.CANCELLED)).collect(Collectors.toList());
        int totalRefund=cancelledBookings.stream().mapToInt(Booking::getTotalPrice).sum();
        SalonReport salonReport=new SalonReport();
        salonReport.setTotalRevenue(totalEarning);
        salonReport.setTotalBookings(totalBookings);
        salonReport.setCancelledBookings(cancelledBookings.size());
        salonReport.setTotalRefund(totalRefund);
        salonReport.setSalonId(salonId);
        return salonReport;

        
    }

}
