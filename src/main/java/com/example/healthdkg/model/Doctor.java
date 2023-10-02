package com.example.healthdkg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int legalCode;

    @Future(message = "License should be valid in the future")
    @NotNull(message = "License validity date is required")
    private LocalDateTime licenseValidTill;

    @ManyToOne
    @JoinColumn(name = "medical_speciality_id")
    @NotNull(message = "Medical speciality is required")
    private MedicalSpeciality medicalSpeciality;

    @OneToOne
    @JoinColumn(name = "person_personal_code")
    @NotNull(message = "Person details are required")
    private Person person;
}
