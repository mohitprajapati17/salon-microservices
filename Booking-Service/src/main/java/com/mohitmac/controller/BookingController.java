
package com.mohitmac.controller;

import java.time.LocalDate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mohitmac.domain.BookingStatus;
import com.mohitmac.domain.PaymentMethod;
import com.mohitmac.service.BookingService;
import com.mohitmac.service.client.PaymentFeignClient;
import com.mohitmac.service.client.SalonFeignClient;
import com.mohitmac.service.client.ServiceOfferringFeignClient;
import com.mohitmac.service.client.UserFeingClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import java.com.mohitmac.domain.BookingStatus;
//import
import com.mohitmac.mapper.BookingMapper;
import com.mohitmac.model.Booking;
import com.mohitmac.model.SalonReport;
import com.mohitmac.payload_DTO.BookingDTO;
import com.mohitmac.payload_DTO.BookingRequest;
import com.mohitmac.payload_DTO.BookingSlotDTO;
import com.mohitmac.payload_DTO.PaymentLinkResponse;
import com.mohitmac.payload_DTO.SalonDTO;
import com.mohitmac.payload_DTO.UserDTO;

import com.mohitmac.payload_DTO.ServiceOfferingDTO;

@RestController
@RequestMapping("/api/booking")

public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    @Autowired 
    private UserFeingClient userFeingClient;

    @Autowired
    private SalonFeignClient salonFeignClient;

    @Autowired 
    private  ServiceOfferringFeignClient serviceOfferringFeignClient;

    @Autowired 

    private PaymentFeignClient paymentFeignClient;

    @PostMapping("")
    public ResponseEntity<PaymentLinkResponse> create( @RequestParam Long  salonId,@RequestParam PaymentMethod paymentMethod,@RequestBody BookingRequest bookingRequest ,@RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO=userFeingClient.getUserByProfile(jwt).getBody();

        SalonDTO salonDTO=salonFeignClient.getSalonById(salonId).getBody();

        Set<ServiceOfferingDTO> serviceOfferingDTOs=serviceOfferringFeignClient.getAllByIds(bookingRequest.getServiceIds()).getBody();

       if(serviceOfferingDTOs.isEmpty()){
        throw new RuntimeException("service not found");
       }
        
        Booking booking =bookingService.createBooking(bookingRequest,userDTO,salonDTO,serviceOfferingDTOs);
        BookingDTO bookingDTO=BookingMapper.toDTO(booking);
        PaymentLinkResponse res=paymentFeignClient.createPayment(bookingDTO, paymentMethod,jwt).getBody();
        return ResponseEntity.ok(res);
    }

    
    private List<BookingDTO> getBookingDTOs(List<Booking>bookings){
        return bookings.stream().map(booking->{return BookingMapper.toDTO(booking);}).collect(Collectors.toList());
    }
    @GetMapping("/customer")
    public ResponseEntity<List<BookingDTO>> getBookingsByCustomer(@RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO user =userFeingClient.getUserByProfile(jwt).getBody();
        List<Booking> bookings =bookingService.getBookingsByCustomerId(user.getId());
        return ResponseEntity.ok(getBookingDTOs(bookings));
    }


    @GetMapping("/salon")
    public ResponseEntity<List<BookingDTO>> getBookingsBySalon(@RequestHeader("Authorization") String jwt) throws Exception{
        SalonDTO salonDTO=salonFeignClient.getSalonByOwnerId(jwt).getBody();
        List<Booking> bookings =bookingService.getBookingsBySalonId(salonDTO.getId());
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
    public ResponseEntity<SalonReport> getSalonReport(@RequestHeader("Authorization") String jwt) throws Exception{
        SalonDTO salonDTO = salonFeignClient.getSalonByOwnerId(jwt).getBody();
        SalonReport report=bookingService.getSalonReport(salonDTO.getId());
        return ResponseEntity.ok(report);
        
  }




}
