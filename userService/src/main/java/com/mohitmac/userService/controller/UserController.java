package com.mohitmac.userService.controller;


import com.mohitmac.userService.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import com.mohitmac.userService.repository.UserRepository;
import com.mohitmac.userService.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;


    

    @GetMapping("")
    public List<Users> getUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("/{userid}")
    public Users getUser(@PathVariable("userid") Long userid ) throws Exception{
        return userService.getUserById(userid);
    }

    @PostMapping("")
    public Users createUser(@RequestBody @Valid Users users){
        return userService.createUser(users);
    }

    @PostMapping("/{id}")
    public Users updateUser(@RequestBody Users users , @PathVariable("id") Long id) throws Exception{
        
        return userService.updateUser(users,id);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) throws Exception{
        userService.deleteUser(id);

    }




    @GetMapping("/profile")
    public ResponseEntity<Users>  getUserByProfile(@RequestHeader("Authorization") String jwt) throws Exception{

        return ResponseEntity.ok(userService.getUserFromJWT(jwt));
    }

}
