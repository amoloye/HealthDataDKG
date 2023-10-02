package com.example.healthdkg.model;

import java.time.LocalDateTime;
import java.util.List;

public class MedicalData {

    private int medicalDataId;
    private MedicalDataSource source;
    private List<Doctor> doctor;
    private Patient patient;
    private String classifier; //health issue
    private String doctorsReport; //Value
    private LocalDateTime localDateTime;
    private MedicalDataSensitivityLevel sensitivityLevel;



}
