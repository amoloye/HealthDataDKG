package com.example.healthdkg.service;

import com.example.healthdkg.dto.DoctorRequestDto;
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
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final MedicalSpecialityRepository specialityRepository;


    public List<Doctor> createDoctors(List<Doctor> doctors) {
        List<Doctor> savedDoctors = new ArrayList<>();
        for (Doctor doctor : doctors) {
            savedDoctors.add(doctorRepository.save(doctor));
        }
        return savedDoctors;
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor mapDtoToDoctor(DoctorRequestDto dto) {
        Doctor doctor = new Doctor();
        doctor.setLegalCode(dto.getLegalCode());
        doctor.setLicenseValidTill(dto.getLicenseValidTill());

        MedicalSpeciality speciality = specialityRepository.findById(dto.getSpecialityId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Medical Speciality not found for id: " + dto.getSpecialityId()));
        doctor.setMedicalSpeciality(speciality);

        doctor.setPersonalCode(dto.getPersonalCode());
        doctor.setEmailAddress(dto.getEmailAddress());
        doctor.setName(dto.getName());
        doctor.setFamilyName(dto.getFamilyName());

        return doctor;
    }


    public Page<Doctor> getDoctorList (int offset, int pageSize){
        Pageable pageable = PageRequest.of(offset,pageSize);
        return doctorRepository.findAll(pageable);
    }

    public List<Doctor> getDoctorListBySpecialityId(Long specialityId) {
        return doctorRepository.findAllByMedicalSpeciality_SpecialityId(specialityId);
    }






    public Doctor getDoctor(Long doctorId) {
        return doctorRepository.findById(doctorId).orElse(null);
    }

    public boolean isDoctorInList(List<Doctor> doctors, Long doctorId) {
        return doctors.stream()
                .anyMatch(doctor -> doctor.getDoctorId().equals(doctorId));
    }


    public boolean doesDoctorHaveMatchingSpeciality(List<Doctor> doctors, List<Doctor> doctorList) {
        Set<Long> specialityIds = doctorList.stream()
                .map(doctor -> doctor.getMedicalSpeciality().getSpecialityId())
                .collect(Collectors.toSet());

        return doctors.stream()
                .anyMatch(doctor -> specialityIds.contains(doctor.getMedicalSpeciality().getSpecialityId()));
    }




}
