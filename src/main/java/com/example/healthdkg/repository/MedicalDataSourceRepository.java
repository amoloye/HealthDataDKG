package com.example.healthdkg.repository;

import com.example.healthdkg.model.MedicalDataSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalDataSourceRepository extends JpaRepository<MedicalDataSource, Integer> {
}
