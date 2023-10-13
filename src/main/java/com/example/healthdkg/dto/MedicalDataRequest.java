package com.example.healthdkg.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalDataRequest {

    private Long doctorId;
    private List<Long> patientIds;
    private int offset;
    private int pageSize;


}
