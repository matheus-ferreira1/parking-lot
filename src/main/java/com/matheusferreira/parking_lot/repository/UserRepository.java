package com.matheusferreira.parking_lot.repository;

import com.matheusferreira.parking_lot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}