package com.example.healthdkg.controller;


import com.example.healthdkg.model.Doctor;
import com.example.healthdkg.model.Patient;
import com.example.healthdkg.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/patients")
    public ResponseEntity<List<Patient>> savePatientList (@Valid @RequestBody List<Patient> patients){
        return new ResponseEntity<>(patientService.createPatient(patients), HttpStatus.OK);

    }


    @GetMapping("/patient")
    public ResponseEntity<Page<Patient>> getPatientList (@RequestParam(defaultValue = "0") int offset,
                                                       @RequestParam(defaultValue = "3") int pageSize){
        return ResponseEntity.ok().body(patientService.getPatientList(offset, pageSize));
    }

}
