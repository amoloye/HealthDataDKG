package com.example.healthdkg.service;


import com.example.healthdkg.model.Doctor;
import com.example.healthdkg.model.Patient;
import com.example.healthdkg.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<Patient> createPatient (List<Patient> patients){
        return patientRepository.saveAll(patients);
    }


    public Page<Patient> getPatientList (int offset, int pageSize){
        Pageable pageable = PageRequest.of(offset,pageSize);
        return patientRepository.findAll(pageable);
    }

    public Patient getPatient (Long patientId){
        return patientRepository.findByPatientId(patientId);
    }

}
