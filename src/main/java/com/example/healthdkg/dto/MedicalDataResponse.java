package com.example.healthdkg.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalDataResponse {

    private Long medicalDataId;
    private List<Long> doctorId;
    private Long patientId;
    private String classifier; //health issue
    private String doctorsReport; //Value

}
