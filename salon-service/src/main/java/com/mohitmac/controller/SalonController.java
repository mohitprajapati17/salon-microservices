package com.mohitmac.controller;

import java.util.List;

import com.mohitmac.service.client.UserFeingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mohitmac.mapper.salonMapper;
import com.mohitmac.model.Salon;
import com.mohitmac.payload_DTO.SalonDTO;
import com.mohitmac.payload_DTO.UserDTO;
import com.mohitmac.service.ServiceSalon;


@RestController
@RequestMapping("/api/salon")
public class SalonController {

    @Autowired
    private ServiceSalon salonService;
    @Autowired
    private UserFeingClient userFeingClient;

    @GetMapping({"", "/"})
    public ResponseEntity<List<SalonDTO>> getAllSalons(){
       
        List<Salon> salons = salonService.getAllSalons();
        List<SalonDTO> salonDTOs=salons.stream().map((salon)->{
            SalonDTO salonDTO=salonMapper.toSalonDTO(salon);
            return salonDTO;
        }).toList();
        return ResponseEntity.ok(salonDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long id) throws Exception{
        
        Salon salon =salonService.getSalonById(id);
        SalonDTO salonDTO=salonMapper.toSalonDTO(salon);
        return ResponseEntity.ok(salonDTO);
    }

    @PostMapping()
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO , @RequestHeader("Authorization") String jwt) throws Exception {
//        UserDTO  userDTO=new UserDTO();
//        userDTO.setId(1L);
        UserDTO userDTO= userFeingClient.getUserByProfile(jwt).getBody();
        Salon salon =salonService.createSalon(salonDTO, userDTO);
        SalonDTO salonDTO2=salonMapper.toSalonDTO(salon);
        return ResponseEntity.ok(salonDTO2);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SalonDTO> update(@PathVariable Long id, @RequestBody SalonDTO salonDTO ,@RequestHeader("Authorization")  String  jwt  ) throws Exception{

        UserDTO userDTO = userFeingClient.getUserByProfile(jwt).getBody();


        Salon salon =salonService.updateSalon(salonDTO, userDTO ,id);
        SalonDTO salonDTO1=salonMapper.toSalonDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> search(@RequestParam("city") String city) {
       
        List<Salon> salons =salonService.getSalonsByCity(city);
        List<SalonDTO> salonDTOs=salons.stream().map((salon)->{
            SalonDTO salonDTO=salonMapper.toSalonDTO(salon);
            return salonDTO;
        }).toList();

        return ResponseEntity.ok(salonDTOs);
    }

    @GetMapping("/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(@RequestHeader ("Authorization") String jwt) throws Exception {
//        UserDTO userDTO =new UserDTO();
//        userDTO.setId(1L);
        UserDTO userDTO= userFeingClient.getUserByProfile(jwt).getBody();


        Salon salon =salonService.getSalonByOwnerId(userDTO.getId());
        SalonDTO salonDTO=salonMapper.toSalonDTO(salon);


        return ResponseEntity.ok(salonDTO);
    }
    
    
}
