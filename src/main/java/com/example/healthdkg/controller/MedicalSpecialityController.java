package com.example.healthdkg.controller;


import com.example.healthdkg.model.Doctor;
import com.example.healthdkg.model.MedicalSpeciality;
import com.example.healthdkg.service.MedicalSpecialityService;
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
public class MedicalSpecialityController {
   private final MedicalSpecialityService specialityService;

    @PostMapping("/specialities")
    public ResponseEntity<List<MedicalSpeciality>> saveSpecialities (@Valid @RequestBody List<MedicalSpeciality>
                                                                                 specialities){
        return new ResponseEntity<>(specialityService.createSpecialities(specialities), HttpStatus.OK);

    }
}
