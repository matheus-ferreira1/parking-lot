package com.matheusferreira.parking_lot.web.controller;

import com.matheusferreira.parking_lot.entity.User;
import com.matheusferreira.parking_lot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }


    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updatePassword(id, user.getPassword());
        return ResponseEntity.ok(updatedUser);
    }
}
