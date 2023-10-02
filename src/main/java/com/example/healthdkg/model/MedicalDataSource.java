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
public class MedicalDataSource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean enabled;
}
