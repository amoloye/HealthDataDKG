package com.example.healthdkg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private Long patientId;

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

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "medical_data_id")
    private List<MedicalData> medicalDataList;

}
