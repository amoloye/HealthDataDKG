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
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MedicalDataService {

    private final MedicalDataRepository medicalDataRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorService doctorService;


    private MedicalData convertToMedicalData(MedicalDataDto medicalDataDto) {
        MedicalData medicalData = new MedicalData();
        medicalData.setClassifier(medicalDataDto.getClassifier());
        medicalData.setDoctorsReport(medicalDataDto.getDoctorsReport());
        medicalData.setSensitivityLevel(
                MedicalDataSensitivityLevel.fromValue(medicalDataDto.getSensitivityLevelValue()));


        // Set other fields

        return medicalData;
    }

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


    //main fucntion to query medical data

    public Page<MedicalDataResponse> getMedicalDataBySensitivityLevel(
            MedicalDataRequest request, int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset, pageSize);

        Long doctorId = request.getDoctorId();

        Doctor doctor = doctorService.getDoctor(doctorId);

        List<Doctor> doctorList = doctorService.
                getDoctorListBySpecialityId(doctor.getMedicalSpeciality().getSpecialityId());

        List<MedicalData> medicalDataList = getMedicalDataByPatientIds(request);



        List<MedicalData> validMedicalData = filterValidMedicalData(medicalDataList, doctorId, doctorList);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), validMedicalData.size());

        List<MedicalData> paginatedMedicalData = validMedicalData.subList(start, end);

        List<MedicalDataResponse> medicalDataResponses = paginatedMedicalData.stream()
                .map(this::convertMedicalDataToResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(medicalDataResponses, pageable, validMedicalData.size());
    }



    public List<MedicalData> getMedicalDataByPatientIds(MedicalDataRequest request) {
        List<Long> patientIds = request.getPatientIds();
        return medicalDataRepository.findByPatientIds(patientIds);
    }


    public MedicalDataResponse convertMedicalDataToResponse(MedicalData medicalData) {
        List<Long> doctorIds = medicalData.getDoctors().stream()
                .map(Doctor::getDoctorId)
                .collect(Collectors.toList());

        return MedicalDataResponse.builder()
                .medicalDataId(medicalData.getMedicalDataId())
                .doctorId(doctorIds)
                .patientId(medicalData.getPatient().getPatientId())
                .classifier(medicalData.getClassifier())
                .doctorsReport(medicalData.getDoctorsReport())
                .build();
    }




    public List<MedicalData> filterValidMedicalData(List<MedicalData> medicalDataList,
                                                    Long doctorId, List<Doctor> doctorList) {
        return medicalDataList.stream()
                .filter(medicalData -> {
                    MedicalDataSensitivityLevel dataSensitivityLevel = medicalData.getSensitivityLevel();
                    List<Doctor> doctorsInMedicalData = medicalData.getDoctors();

                    return switch (dataSensitivityLevel) {
                        case CLOSED -> false;

                        case OPEN_TO_SPECIFIC_DOCTOR ->
                                doctorService.isDoctorInList(doctorsInMedicalData, doctorId);

                        case OPEN_TO_MEDICAL_SPECIALITY ->
                                doctorService.doesDoctorHaveMatchingSpeciality(doctorsInMedicalData, doctorList);

                        case OPEN_TO_EVERYONE -> true;
                        default -> false;
                    };
                })
                .collect(Collectors.toList());
    }



}
