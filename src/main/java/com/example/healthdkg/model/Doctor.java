package com.example.healthdkg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "legal_code")
    private String legalCode;

    @Future(message = "License should be valid in the future")
    @NotNull(message = "License validity date is required")
    @Column(name = "license_valid_till")
    private LocalDateTime licenseValidTill;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="speciality_id", referencedColumnName = "speciality_id")
    @NotNull(message = "Medical speciality is required")
    private MedicalSpeciality medicalSpeciality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @NotNull(message = "Person details are required")
    private Person person;

    // Getters and setters
}
