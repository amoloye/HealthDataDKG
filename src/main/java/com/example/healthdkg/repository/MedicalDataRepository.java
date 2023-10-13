package com.example.healthdkg.repository;


import com.example.healthdkg.model.MedicalData;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface MedicalDataRepository extends JpaRepository<MedicalData, Long> {

    List<MedicalData> findAllByPatientPatientId(Long patientId);


    @Query(value = "SELECT * FROM medical_data WHERE patient_id IN :patientIds", nativeQuery = true)
    List<MedicalData> findByPatientIds(@Param("patientIds") List<Long> patientIds);

}

