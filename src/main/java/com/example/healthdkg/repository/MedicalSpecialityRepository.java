package com.example.healthdkg.repository;


import com.example.healthdkg.model.MedicalSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MedicalSpecialityRepository extends JpaRepository<MedicalSpeciality, Long> {

    Optional<MedicalSpeciality> findBySpecialityName (String specialityName);
}
