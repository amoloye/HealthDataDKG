package com.example.healthdkg.repository;

import com.example.healthdkg.model.MedicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalDataRepository extends JpaRepository<MedicalData, Integer> {
}

