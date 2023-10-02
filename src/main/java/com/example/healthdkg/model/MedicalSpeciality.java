package com.example.healthdkg.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class MedicalSpeciality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int specialityId;
    private String specialityName;

}
