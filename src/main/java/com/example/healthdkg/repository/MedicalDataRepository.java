package com.example.healthdkg.repository;

import com.example.healthdkg.model.MedicalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalDataRepository extends JpaRepository<MedicalData, Integer> {
}

