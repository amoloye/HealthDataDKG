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

    public List<Doctor> createDoctors (List<Doctor> doctors){
        return doctorRepository.saveAll(doctors);
    }

    public Doctor createDoctor (Doctor doctor){
        return  doctorRepository.save(doctor);

    }


}
