package com.example.healthdkg.repository;


import com.example.healthdkg.model.MedicalData;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalDataRepository extends JpaRepository<MedicalData, Long> {



    @Query(value = "SELECT * FROM health_data.medical_data WHERE patient_id IN :patientIds", nativeQuery = true)
    List<MedicalData> findAllByPatientIds(@Param("patientIds") List<Long> patientIds);




}

