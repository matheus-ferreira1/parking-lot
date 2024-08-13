package com.matheusferreira.parking_lot.web.controller;

import com.matheusferreira.parking_lot.entity.User;
import com.matheusferreira.parking_lot.service.UserService;
import com.matheusferreira.parking_lot.web.DTO.UserCreateDTO;
import com.matheusferreira.parking_lot.web.DTO.UserPasswordDTO;
import com.matheusferreira.parking_lot.web.DTO.UserResponseDTO;
import com.matheusferreira.parking_lot.web.DTO.mapper.UserMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(UserMapper.toListDTO(users));
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        User newUser = userService.create(UserMapper.toUser(userCreateDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDTO(newUser));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UserPasswordDTO dto) {
        userService.updatePassword(id, dto.getCurrentPassword(), dto.getNewPassword(),
                dto.getPasswordConfirmation());
        return ResponseEntity.noContent().build();
    }
}
