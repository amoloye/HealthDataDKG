package com.example.healthdkg.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "medical_speciality")
public class MedicalSpeciality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "speciality_id")
    private Long specialityId;

    @NotBlank(message = "Speciality name is required")
    @Column(name = "speciality_name")
    private String specialityName;

    // Getters and setters
}
