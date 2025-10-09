package com.mohitmac.userService.service;

import com.mohitmac.userService.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mohitmac.userService.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found: " + id));
    }

    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    public Users updateUser(Users user,Long id) throws Exception {
        Users existing = userRepository.findById(id).orElseThrow(() -> new Exception("User not found: " + user.getId()));
        existing.setFullName(user.getFullName());
        existing.setPhone(user.getPhone());
        existing.setRole(user.getRole());
        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        existing.setUpdatedAt(user.getUpdatedAt());
        return userRepository.save(existing);
    }

    public void deleteUser(Long id) throws Exception {
        Users existing = userRepository.findById(id).orElseThrow(() -> new Exception("User not found: " + id));
        userRepository.delete(existing);
    }
}
