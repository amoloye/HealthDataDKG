package com.example.healthdkg.service;


import com.example.healthdkg.model.MedicalSpeciality;
import com.example.healthdkg.repository.MedicalSpecialityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalSpecialityService {

    private final MedicalSpecialityRepository specialityRepository;

    public List<MedicalSpeciality> createSpecialities(List<MedicalSpeciality> specialities) {
        try {
            return specialityRepository.saveAll(specialities);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create specialities: " + e.getMessage());
        }
    }

    public MedicalSpeciality createSpeciality(MedicalSpeciality speciality) {
        try {
            return specialityRepository.save(speciality);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create speciality: " + e.getMessage());
        }
    }
}

