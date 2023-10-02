package com.example.healthdkg.service;

import com.example.healthdkg.model.Doctor;
import com.example.healthdkg.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<Doctor> createDoctor (List<Doctor> doctors){
        return doctorRepository.saveAll(doctors);
    }


}
