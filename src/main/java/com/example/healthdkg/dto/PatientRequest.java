package com.example.healthdkg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
    private List<Long> patientIds;

    public List<Long> getPatientIds() {
        return patientIds;
    }

}

