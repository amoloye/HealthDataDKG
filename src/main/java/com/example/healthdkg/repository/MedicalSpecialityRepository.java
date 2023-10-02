package com.example.healthdkg.repository;


import com.example.healthdkg.model.MedicalSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalSpecialityRepository extends JpaRepository<MedicalSpeciality, Integer> {
}
