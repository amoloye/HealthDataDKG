package com.example.healthdkg.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class MedicalData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medical_data_id")
    private int medicalDataId;

    @OneToOne
    @JoinColumn(name = "source_id")
    private MedicalDataSource source;

    @OneToMany
    @JoinColumn(name = "doctor_id")
    private List<Doctor> doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String classifier; //health issue
    private String doctorsReport; //Value
    private LocalDateTime localDateTime;
    private MedicalDataSensitivityLevel sensitivityLevel;



}
