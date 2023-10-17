package com.example.healthdkg.repository;

import com.example.healthdkg.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAll (Pageable pageable);


    @Query(value = "SELECT * FROM health_data.doctor d WHERE d.speciality_id = :specialityId", nativeQuery = true)
    List<Doctor> findAllByMedicalSpeciality_SpecialityId(@Param("specialityId") Long specialityId);


}
