package com.aykutsert.controller;

import com.aykutsert.entity.User;
import com.aykutsert.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;


    @Autowired
    public UserController(UserRepository userRepository) {
    this.userRepository=userRepository;

    }

    @PostMapping("/save")
    public ResponseEntity<User> addUser(@RequestBody  User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());

    }
    @GetMapping("/test")
    public String test() {
        return "test";

    }
}
