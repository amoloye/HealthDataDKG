package com.example.healthdkg.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int patientId;

    @OneToOne
    @JoinColumn(name = "person_personal_code")
    private Person person;

    @OneToMany
    @JoinColumn(name = "medical_data_id")
    private List<MedicalData> medicalDataList;

}
