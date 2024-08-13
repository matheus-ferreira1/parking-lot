package com.matheusferreira.parking_lot.web.DTO;

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
public class UserPasswordDTO {
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 6, message = "Password must have 6 characters")
    private String currentPassword;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 6, message = "Password must have 6 characters")
    private String newPassword;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 6, message = "Password must have 6 characters")
    private String passwordConfirmation;
}
