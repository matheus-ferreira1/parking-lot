package com.matheusferreira.parking_lot.service;

import com.matheusferreira.parking_lot.entity.User;
import com.matheusferreira.parking_lot.exception.EntityNotFoundException;
import com.matheusferreira.parking_lot.exception.InvalidPasswordException;
import com.matheusferreira.parking_lot.exception.UsernameUniqueViolationException;
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
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %d not found", id)));
    }

    @Transactional
    public User create(User user) {
        try {
            return userRepository.save(user);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Username %s already exists", user.getUsername()));
        }
    }

    @Transactional
    public User updatePassword(Long id, String currentPassword, String newPassword, String passwordConfirmation) {
        if (!newPassword.equals(passwordConfirmation)) {
            throw new InvalidPasswordException("Passwords do not match");
        }

        User user = getById(id);
        if (!user.getPassword().equals(currentPassword)) {
            throw new InvalidPasswordException("Invalid credentials");
        }

        user.setPassword(newPassword);
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
