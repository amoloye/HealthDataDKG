package com.example.healthdkg.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "medical_data")
public class MedicalData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medical_data_id")
    private int medicalDataId;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="source_id")
    private MedicalDataSource source;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="doctor_id")
    private List<Doctor> doctor;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String classifier; //health issue
    private String doctorsReport; //Value
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    private MedicalDataSensitivityLevel sensitivityLevel;



}
