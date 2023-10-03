package com.example.healthdkg.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "medical_data_source")
public class MedicalDataSource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "source_id")
    private int medicalDataSourceId;

    private boolean enabled;
}
