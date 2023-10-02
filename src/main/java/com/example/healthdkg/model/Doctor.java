package com.example.healthdkg.model;

import com.example.healthdkg.dto.Person;

import java.time.LocalDateTime;

public class Doctor {

    private int legalCode;
    private LocalDateTime licenseValidTill;
    private MedicalSpeciality medicalSpeciality;
    private Person person;
}
