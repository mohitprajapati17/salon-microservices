
package main.java.com.mohitmac.mapper;
import main.java.com.mohitmac.model.Booking;
import main.java.com.mohitmac.payload_DTO.BookingDTO;
import main.java.com.mohitmac.domain.BookingStatus;

public class BookingMapper {

    public static BookingDTO toDTO(Booking booking){
        BookingDTO bookingDTO =new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setSalonId(booking.getSalonId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setServiceIds(booking.getServiceIds());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setTotalPrice(booking.getTotalPrice());
        return bookingDTO;
    }
    
}