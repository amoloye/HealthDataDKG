package com.example.healthdkg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor

public class MedicalDataDto {

    @NotNull(message = "Doctor IDs are required")
    @Size(min = 1, message = "At least one Doctor ID is required")
    private List<Long> doctorIds;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotBlank(message = "Classifier is required, note classifier categorizes the ailment")
    private String classifier;

    @NotBlank(message = "Doctors Report is required")
    private String doctorsReport;

    private LocalDateTime localDateTime=LocalDateTime.now();

    @NotNull(message = "Sensitivity Level Value is required")
    @Range(min=0, max=3, message = "Sensitivity level has to be between 0-3")
    private int sensitivityLevelValue;

}
