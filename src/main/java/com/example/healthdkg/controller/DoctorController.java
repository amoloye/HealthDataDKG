package com.example.healthdkg.controller;


import com.example.healthdkg.model.Doctor;
import com.example.healthdkg.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/doctors")
    public ResponseEntity<List<Doctor>> saveDoctors (@Valid @RequestBody  List<Doctor> doctors){
        return new ResponseEntity<>(doctorService.createDoctors(doctors), HttpStatus.OK);

    }

    @PostMapping("/doctor")
    public ResponseEntity<Doctor> saveDoctor (@Valid @RequestBody  Doctor doctor){
        return new ResponseEntity<>(doctorService.createDoctor(doctor), HttpStatus.OK);

    }



}
