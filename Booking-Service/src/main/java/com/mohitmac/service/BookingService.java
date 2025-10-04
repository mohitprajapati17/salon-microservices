

package com.mohitmac.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.mohitmac.domain.BookingStatus;
import com.mohitmac.model.Booking;
import com.mohitmac.model.SalonReport;
import com.mohitmac.payload_DTO.BookingRequest;
import com.mohitmac.payload_DTO.SalonDTO;
import com.mohitmac.payload_DTO.ServiceOfferingDTO;
import com.mohitmac.payload_DTO.UserDTO;

public interface BookingService {
    
    Booking createBooking(BookingRequest bookingRequest,UserDTO userDTO,SalonDTO salonDTO,Set<ServiceOfferingDTO> setserviceOfferingDTO); 
    List<Booking> getBookingsByCustomerId(Long customerId);
    Booking getBookingById(Long bookingId);
    List<Booking> getBookingsBySalonId(Long salonId);
    Booking updateBooking(Long bookingId,BookingStatus status);
    List<Booking> getBookingsByDate(LocalDate date,Long salonId);
    SalonReport getSalonReport(Long salonId);

}
