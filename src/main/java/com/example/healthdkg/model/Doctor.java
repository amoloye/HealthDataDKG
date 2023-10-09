package com.example.healthdkg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name="speciality_id", referencedColumnName = "speciality_id")
    private MedicalSpeciality medicalSpeciality;

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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "doctor_medical_data",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_data_id")
    )
    private List<MedicalData> medicalDataList;
}

