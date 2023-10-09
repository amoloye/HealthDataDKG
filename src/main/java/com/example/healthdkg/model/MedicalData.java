package com.example.healthdkg.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "medical_data")
public class MedicalData {

    public MedicalData ( List<Doctor> doctors, Patient patient,
                        String classifier, String doctorsReport, LocalDateTime localDateTime,
                        MedicalDataSensitivityLevel sensitivityLevel) {
        this.doctors = doctors;
        this.patient = patient;
        this.classifier = classifier;
        this.doctorsReport = doctorsReport;
        this.localDateTime = localDateTime;
        this.sensitivityLevel = sensitivityLevel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medical_data_id")
    private Long medicalDataId;


    @ManyToMany
    private List<Doctor> doctors;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String classifier; //health issue
    private String doctorsReport; //Value
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    private MedicalDataSensitivityLevel sensitivityLevel;


    public void setDoctor(List<Doctor> doctor) {
        this.doctors = doctor;
    }

}
