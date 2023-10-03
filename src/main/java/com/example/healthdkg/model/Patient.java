package com.example.healthdkg.model;

import jakarta.persistence.*;
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
    private int patientId;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany
    @JoinColumn(name = "medical_data_id")
    private List<MedicalData> medicalDataList;

}
