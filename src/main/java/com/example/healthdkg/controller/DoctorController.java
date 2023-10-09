package com.example.healthdkg.controller;


import com.example.healthdkg.model.Doctor;
import com.example.healthdkg.service.DoctorService;
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
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/doctors")
    public ResponseEntity<List<Doctor>> saveDoctors(@RequestBody List<Doctor> doctors) {
        List<Doctor> savedDoctors = doctorService.createDoctors(doctors);
        return new ResponseEntity<>(savedDoctors, HttpStatus.OK);
    }


    @PostMapping("/doctor")
    public ResponseEntity<Doctor> saveDoctor( @RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.OK);
    }

    @GetMapping("/doctor")
    public ResponseEntity<Page<Doctor>> getDoctorList (@RequestParam(defaultValue = "0") int offset,
                                                       @RequestParam(defaultValue = "3") int pageSize){
        return ResponseEntity.ok().body(doctorService.getDoctorList(offset,pageSize));
    }


}
