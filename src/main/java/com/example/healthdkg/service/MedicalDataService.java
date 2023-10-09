package com.example.healthdkg.service;

import com.example.healthdkg.dto.MedicalDataDto;
import com.example.healthdkg.dto.MedicalDataRequest;
import com.example.healthdkg.dto.MedicalDataResponse;
import com.example.healthdkg.model.*;
import com.example.healthdkg.repository.DoctorRepository;
import com.example.healthdkg.repository.MedicalDataRepository;
import com.example.healthdkg.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MedicalDataService {

    private final MedicalDataRepository medicalDataRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorService doctorService;

    public void saveMedicalData(MedicalDataDto medicalDataDto) {
        MedicalData medicalData = convertToMedicalData(medicalDataDto);

        // Associate Doctors with MedicalData
        List<Doctor> doctors = doctorRepository.findAllById(medicalDataDto.getDoctorIds());
        medicalData.setDoctor(doctors);

        medicalDataRepository.save(medicalData);
    }

    public void saveMedicalDataList(List<MedicalDataDto> medicalDataDtoList) {
        List<MedicalData> medicalDataList = medicalDataDtoList.stream()
                .map(this::convertToMedicalData).collect(Collectors.toList());

        medicalDataRepository.saveAll(medicalDataList);
    }

    private MedicalData convertToMedicalData(MedicalDataDto medicalDataDto) {
        MedicalData medicalData = new MedicalData();
        medicalData.setClassifier(medicalDataDto.getClassifier());
        medicalData.setDoctorsReport(medicalDataDto.getDoctorsReport());
        medicalData.setSensitivityLevel(
                MedicalDataSensitivityLevel.valueOf(String.valueOf(medicalDataDto.getSensitivityLevelValue()))
        );

        // Set other fields

        return medicalData;
    }

    private MedicalData getMedicalData (MedicalDataDto dto) {
        Long medicalDataId = dto.getMedicalDataId();
        List<Long> doctorIds = dto.getDoctorIds();
        Long patientId = dto.getPatientId();
        String classifier = dto.getClassifier();
        String doctorsReport = dto.getDoctorsReport();
        int sensitivityLevelValue = dto.getSensitivityLevelValue();

        MedicalDataSensitivityLevel sensitivityLevel =
                MedicalDataSensitivityLevel.fromValue(sensitivityLevelValue);

        List<Doctor> doctors = doctorIds.stream()
                .map(id -> doctorRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID provided.")))
                .collect(Collectors.toList());

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID provided."));

        return new MedicalData(
                medicalDataId,
                doctors,
                patient,
                classifier,
                doctorsReport,
                LocalDateTime.now(),
                sensitivityLevel
        );
    }


    private List<MedicalData> getMedicalDataForPatient(Long patientId) {
        return medicalDataRepository.findAllByPatientPatientId(patientId);
    }


    private MedicalDataResponse convertMedicalDataToResponse(MedicalData medicalData) {
        MedicalDataResponse response = new MedicalDataResponse();
        response.setMedicalDataId(medicalData.getMedicalDataId());

        List<Long> doctorIds = medicalData.getDoctors().stream()  // Updated this line
                .map(Doctor::getDoctorId)
                .collect(Collectors.toList());

        response.setDoctorId(doctorIds);
        response.setPatientId(medicalData.getPatient().getPatientId());
        response.setClassifier(medicalData.getClassifier());
        response.setDoctorsReport(medicalData.getDoctorsReport());

        return response;
    }



    public Page<MedicalDataResponse> getMedicalDataBySensitivityLevel(
            MedicalDataRequest request, int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset, pageSize);

        Long doctorId = request.getDoctorId();
        List<Long> patientIds = request.getPatientIds();

        Doctor doctor = doctorService.getDoctor(doctorId);
        MedicalSpeciality speciality = doctor.getMedicalSpeciality();

        List<MedicalDataResponse> validMedicalDataResponses = new ArrayList<>();

        for (Long patientId : patientIds) {
            List<MedicalData> medicalDataList = getMedicalDataForPatient(patientId);

            for (MedicalData medicalData : medicalDataList) {
                MedicalDataSensitivityLevel sensitivityLevel = medicalData.getSensitivityLevel();
                List<Doctor> doctorsInMedicalData = medicalData.getDoctors(); // Updated here

                switch (sensitivityLevel) {
                    case CLOSED:
                        break;
                    case OPEN_TO_SPECIFIC_DOCTOR:
                        if (doctorService.isDoctorInList(doctorsInMedicalData, doctorId)) {
                            validMedicalDataResponses.add(convertMedicalDataToResponse(medicalData));
                        }
                        break;
                    case OPEN_TO_MEDICAL_SPECIALITY:
                        if (doctorService.doesDoctorHaveMatchingSpeciality(doctorsInMedicalData, speciality)) {
                            validMedicalDataResponses.add(convertMedicalDataToResponse(medicalData));
                        }
                        break;
                    case OPEN_TO_EVERYONE:
                        validMedicalDataResponses.add(convertMedicalDataToResponse(medicalData));
                        break;
                }
            }
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), validMedicalDataResponses.size());

        return new PageImpl<>(validMedicalDataResponses.subList(start, end), pageable, validMedicalDataResponses.size());
    }

}
