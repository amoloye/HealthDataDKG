package com.example.healthdkg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor

public class MedicalDataDto {
    public MedicalDataDto (Long medicalDataId, List<Long> doctorIds,
                           Long patientId, String classifier, String doctorsReport,
                           LocalDateTime localDateTime, int sensitivityLevelValue) {
        this.medicalDataId = medicalDataId;
        this.doctorIds = doctorIds;
        this.patientId = patientId;
        this.classifier = classifier;
        this.doctorsReport = doctorsReport;
        this.localDateTime = LocalDateTime.now();
        this.sensitivityLevelValue = sensitivityLevelValue;
    }

    private Long medicalDataId;

    @NotNull(message = "Doctor IDs are required")
    @Size(min = 1, message = "At least one Doctor ID is required")
    private List<Long> doctorIds;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotBlank(message = "Classifier is required, note classifier categorizes the ailment")
    private String classifier;

    @NotBlank(message = "Doctors Report is required")
    private String doctorsReport;

    @NotNull(message = "Local Date Time is required")
    private LocalDateTime localDateTime;

    @NotNull(message = "Sensitivity Level Value is required")
    @Range(min=0, max=3, message = "Sensitivity level has to be between 0-3")
    private int sensitivityLevelValue;

}
