package com.matheusferreira.parking_lot.service;

import com.matheusferreira.parking_lot.entity.User;
import com.matheusferreira.parking_lot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updatePassword(Long id, String currentPassword, String newPassword, String passwordConfirmation) {
        if (!newPassword.equals(passwordConfirmation)) {
            throw new RuntimeException("Passwords do not match");
        }

        User user = getById(id);
        if (!user.getPassword().equals(currentPassword)) {
            throw new RuntimeException("Invalid credentials");
        }

        user.setPassword(newPassword);
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
