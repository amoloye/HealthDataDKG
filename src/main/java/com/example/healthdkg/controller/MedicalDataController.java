package com.example.healthdkg.controller;

import com.example.healthdkg.dto.MedicalDataDto;
import com.example.healthdkg.dto.MedicalDataRequest;
import com.example.healthdkg.dto.MedicalDataResponse;
import com.example.healthdkg.dto.PatientRequest;
import com.example.healthdkg.model.MedicalData;
import com.example.healthdkg.service.MedicalDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class MedicalDataController {

    private final MedicalDataService medicalDataService;

    // Endpoint to save a single MedicalData
    @PostMapping("/person-health-data")
    public ResponseEntity<String> addMedicalData(@Valid @RequestBody MedicalDataDto medicalDataDto) {
        medicalDataService.saveMedicalData(medicalDataDto);
        return ResponseEntity.ok("Medical Data saved successfully.");
    }

    // Endpoint to save a list of MedicalData
    @PostMapping("/person-health-data-list")
    public ResponseEntity<String> addMedicalDataList(@Valid @RequestBody List<MedicalDataDto> medicalDataDtoList) {
        medicalDataService.saveMedicalDataList(medicalDataDtoList);
        return ResponseEntity.ok("Medical Data saved successfully.");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/get-health-data-list-by-sensitivity")
    public ResponseEntity<Page<MedicalDataResponse>> getMedicalDataBySensitivityLevel(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "5") int pageSize,
            MedicalDataRequest request) {

        Page<MedicalDataResponse> medicalData =
                medicalDataService.getMedicalDataBySensitivityLevel(request, offset, pageSize);
        return ResponseEntity.ok(medicalData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/get-by-patient-ids")
    public ResponseEntity<List<MedicalData>> getMedicalDataByPatientIds(PatientRequest patientRequest) {
        List<Long> patientIds = patientRequest.getPatientIds();
        List<MedicalData> medicalDataList = medicalDataService.getMedicalDataByPatientIdList(patientRequest);
        return ResponseEntity.ok(medicalDataList);
    }


}
