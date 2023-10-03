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

    public List<MedicalSpeciality> createSpecialities (List<MedicalSpeciality> specialities){
        return specialityRepository.saveAll(specialities);
    }

    public MedicalSpeciality createSpeciality (MedicalSpeciality speciality){
        return specialityRepository.save(speciality);
    }

}
