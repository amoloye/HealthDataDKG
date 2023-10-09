package com.example.healthdkg.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDto {
    private String legalCode;

    @Future(message = "License should be valid in the future")
    @NotNull(message = "License validity date is required")
    private LocalDateTime licenseValidTill;

    private Long specialityId;

    @NotBlank(message = "Personal code is required")
    @Pattern(regexp = "[0-9]{11}", message = "Personal code should be 11 digits")
    private String personalCode;

    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email address")
    private String emailAddress;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Family name is required")
    private String familyName;
}
