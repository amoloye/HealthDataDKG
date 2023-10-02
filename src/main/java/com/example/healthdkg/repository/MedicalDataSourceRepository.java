package com.example.healthdkg.repository;

import com.example.healthdkg.model.MedicalDataSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalDataSourceRepository extends JpaRepository<MedicalDataSource, Integer> {
}
