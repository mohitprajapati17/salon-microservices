

package main.java.com.mohitmac.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import main.java.com.mohitmac.domain.BookingStatus;
import main.java.com.mohitmac.model.Booking;
import main.java.com.mohitmac.model.SalonReport;
import main.java.com.mohitmac.payload_DTO.BookingRequest;
import main.java.com.mohitmac.payload_DTO.SalonDTO;
import main.java.com.mohitmac.payload_DTO.ServiceOfferingDTO;
import main.java.com.mohitmac.payload_DTO.UserDTO;

public interface BookingService {
    
    Booking createBooking(BookingRequest bookingRequest,UserDTO userDTO,SalonDTO salonDTO,Set<ServiceOfferingDTO> setserviceOfferingDTO); 
    List<Booking> getBookingsByCustomerId(Long customerId);
    Booking getBookingById(Long bookingId);
    List<Booking> getBookingsBySalonId(Long salonId);
    Booking updateBooking(Long bookingId,BookingStatus status);
    List<Booking> getBookingsByDate(LocalDate date,Long salonId);
    SalonReport getSalonReport(Long salonId);

}
