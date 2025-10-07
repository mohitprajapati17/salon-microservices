package controller;


import model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import repository.UserRepository;
import service.UserService;

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
}
