package com.example.healthdkg.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @NotBlank(message = "Personal code is required")
    @Pattern(regexp = "[0-9]{11}", message = "Personal code should be 11 digits")
    private String personalCode;

    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email address")
    @Column(name = "email_address")
    private String emailAddress;

    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Family name is required")
    @Column(name = "family_name")
    private String familyName;

    // Getters and setters
}