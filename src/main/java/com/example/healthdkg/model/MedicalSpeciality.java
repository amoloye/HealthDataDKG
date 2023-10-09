package com.example.healthdkg.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "medical_speciality")
public class MedicalSpeciality {

    @Id
    @Column(name = "speciality_id")
    private Long specialityId;

    @NotBlank(message = "Speciality name is required")
    @Column(name = "speciality_name")
    private String specialityName;

}
