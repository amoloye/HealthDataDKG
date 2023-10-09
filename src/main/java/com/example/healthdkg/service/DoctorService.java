package com.example.healthdkg.service;

import com.example.healthdkg.model.Doctor;
import com.example.healthdkg.model.MedicalSpeciality;
import com.example.healthdkg.repository.DoctorRepository;
import com.example.healthdkg.repository.MedicalSpecialityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final MedicalSpecialityRepository specialityRepository;


    public List<Doctor> createDoctors(List<Doctor> doctors) {
        List<Doctor> savedDoctors = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (checkSpecialityExistence(doctor)) {
                savedDoctors.add(doctorRepository.save(doctor));
            } else {
                throw new IllegalArgumentException("Medical Speciality not found for id: " + doctor.getMedicalSpeciality().getSpecialityId());
            }
        }
        return savedDoctors;
    }

    public Doctor createDoctor(Doctor doctor) {
        if (checkSpecialityExistence(doctor)) {
            return doctorRepository.save(doctor);
        } else {
            throw new IllegalArgumentException("Medical Speciality not found for id: " + doctor.getMedicalSpeciality().getSpecialityId());
        }
    }

    private boolean checkSpecialityExistence(Doctor doctor) {
        MedicalSpeciality medicalSpeciality = doctor.getMedicalSpeciality();
        if (medicalSpeciality != null) {
            Long specialityId = medicalSpeciality.getSpecialityId();
            return specialityRepository.findById(specialityId).isPresent();
        }
        return true; // If no speciality provided, return true
    }



    public Page<Doctor> getDoctorList (int offset, int pageSize){
        Pageable pageable = PageRequest.of(offset,pageSize);
        return doctorRepository.findAll(pageable);
    }


    Doctor getDoctor (Long doctorId) {
        return doctorRepository.findById(doctorId).orElseThrow(
                () -> new IllegalArgumentException("Invalid doctor ID provided.")
        );
    }

    boolean isDoctorInList (List<Doctor> doctors, Long doctorId) {
        return doctors.stream()
                .anyMatch(doc -> doc.getDoctorId().equals(doctorId));
    }

    boolean doesDoctorHaveMatchingSpeciality (List<Doctor> doctors, MedicalSpeciality speciality) {
        return doctors.stream()
                .anyMatch(doc -> doc.getMedicalSpeciality().equals(speciality));
    }

}
