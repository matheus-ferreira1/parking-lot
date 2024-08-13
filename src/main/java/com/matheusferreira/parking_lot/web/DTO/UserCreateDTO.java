package com.matheusferreira.parking_lot.web.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateDTO {
    @NotBlank(message = "Username is required")
    @Email(message = "Invalid email", regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}$")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 6, message = "Password must have 6 characters")
    private String password;
}
