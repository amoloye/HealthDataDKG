package com.example.healthdkg.controller;


import com.example.healthdkg.dto.DoctorRequestDto;
import com.example.healthdkg.model.Doctor;
import com.example.healthdkg.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/doctors")
    public ResponseEntity<List<Doctor>> createDoctors( @RequestBody List<DoctorRequestDto> doctorRequestDtos) {
        List<Doctor> doctors = doctorRequestDtos.stream()
                .map(doctorService::mapDtoToDoctor)
                .collect(Collectors.toList());

        List<Doctor> savedDoctors = doctorService.createDoctors(doctors);

        return new ResponseEntity<>(savedDoctors, HttpStatus.CREATED);
    }

    @PostMapping("/doctor")
    public ResponseEntity<Doctor> createDoctor( @RequestBody DoctorRequestDto doctorRequestDto) {
        Doctor doctor = doctorService.mapDtoToDoctor(doctorRequestDto);
        Doctor savedDoctor = doctorService.createDoctor(doctor);

        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @GetMapping("/doctor")
    public ResponseEntity<Page<Doctor>> getDoctorList (@RequestParam(defaultValue = "0") int offset,
                                                       @RequestParam(defaultValue = "3") int pageSize){
        return ResponseEntity.ok().body(doctorService.getDoctorList(offset,pageSize));
    }


}
