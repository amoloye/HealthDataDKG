package com.example.healthdkg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Column(name = "license_valid_till")
    private LocalDateTime licenseValidTill;

    @ManyToOne
    @JoinColumn(name="speciality_id", referencedColumnName = "speciality_id")
    private MedicalSpeciality medicalSpeciality;

    private String personalCode;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "name")
    private String name;

    @Column(name = "family_name")
    private String familyName;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "doctor_medical_data",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_data_id")
    )
    private List<MedicalData> medicalDataList;
}

