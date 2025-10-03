
package main.java.com.mohitmac.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.mohitmac.domain.BookingStatus;
import main.java.com.mohitmac.mapper.BookingMapper;
import main.java.com.mohitmac.model.Booking;
import main.java.com.mohitmac.model.SalonReport;
import main.java.com.mohitmac.payload_DTO.BookingDTO;
import main.java.com.mohitmac.payload_DTO.BookingRequest;
import main.java.com.mohitmac.payload_DTO.BookingSlotDTO;
import main.java.com.mohitmac.payload_DTO.SalonDTO;
import main.java.com.mohitmac.payload_DTO.UserDTO;
import main.java.com.mohitmac.service.BookingService;
import main.java.com.mohitmac.payload_DTO.ServiceOfferingDTO;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    @PostMapping("")
    public ResponseEntity<Booking> create( @RequestBody BookingRequest bookingRequest){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);

        SalonDTO salonDTO=new SalonDTO();
        salonDTO.setId(1L);
        salonDTO.setOpenTime(LocalTime.of(9, 0));
        salonDTO.setCloseTime(LocalTime.of(18, 0));

        Set<ServiceOfferingDTO> serviceOfferingDTOs=new HashSet<>();

        ServiceOfferingDTO serviceDTO=new ServiceOfferingDTO();
        serviceDTO.setId(1L);
        serviceDTO.setPrice(100);
        serviceDTO.setDuration(60);
        serviceDTO.setName("Haircut");
       
        serviceOfferingDTOs.add(serviceDTO);
        Booking booking =bookingService.createBooking(bookingRequest,userDTO,salonDTO,serviceOfferingDTOs);
        return ResponseEntity.ok(booking);
    }

    
    private List<BookingDTO> getBookingDTOs(List<Booking>bookings){
        return bookings.stream().map(booking->{return BookingMapper.toDTO(booking);}).collect(Collectors.toList());
    }
    @GetMapping("/customer")
    public ResponseEntity<List<BookingDTO>> getBookingsByCustomer(){
        List<Booking> bookings =bookingService.getBookingsByCustomerId(1L);
        return ResponseEntity.ok(getBookingDTOs(bookings));
    }


    @GetMapping("/salon")
    public ResponseEntity<List<BookingDTO>> getBookingsBySalon(){
        List<Booking> bookings =bookingService.getBookingsBySalonId(1L);
        return ResponseEntity.ok(getBookingDTOs(bookings));
    }



    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingsById(@PathVariable Long bookingId){
        Booking booking=bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @PutMapping("/{bookingId}/{status}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long bookingId,@PathVariable BookingStatus status){
        Booking booking=bookingService.updateBooking(bookingId,status);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }
        




    /**
     * @param salonId
     * @param date
     * @return
     */
    @GetMapping("/slots/salons/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getSlots(@PathVariable Long salonId,@RequestParam(required=false) LocalDate date){
        List<Booking> bookings=bookingService.getBookingsBySalonId(salonId);
        List<BookingSlotDTO> slots=bookings.stream().map(booking->{
            BookingSlotDTO bookingSlotDTO=new BookingSlotDTO();
            bookingSlotDTO.setStartTime(booking.getStartTime());
            bookingSlotDTO.setEndTime(booking.getEndTime());
            return bookingSlotDTO;

        } ).collect(Collectors.toList());
        return ResponseEntity.ok(slots);
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport(){
        SalonReport report=bookingService.getSalonReport(1L);
        return ResponseEntity.ok(report);
        

  }




}
