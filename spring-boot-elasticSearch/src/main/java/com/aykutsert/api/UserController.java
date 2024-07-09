package com.aykutsert.api;

import com.aykutsert.entity.User;
import com.aykutsert.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @GetMapping("/{search}")
    public ResponseEntity<List<User>> getUser(@PathVariable String search) {
        List<User> users = userRepo.findByAdLikeOrSoyadLike(search, search);
        return ResponseEntity.ok(users);
    }
    @PostMapping("/save")
    public ResponseEntity<User> setUser(@RequestBody User user) {

        return ResponseEntity.ok(userRepo.save(user));
    }
}