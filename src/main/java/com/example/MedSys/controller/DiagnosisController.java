package com.example.MedSys.controller;

import com.example.MedSys.domain.Diagnosis;
import com.example.MedSys.domain.User;
import com.example.MedSys.repository.DiagnosisRepository;
import com.example.MedSys.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/diagnosis")
public class DiagnosisController {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Autowired
    private DiagnosisService diagnosisService;

    @GetMapping
    public List<Diagnosis> getAllUsersEvent(@AuthenticationPrincipal User user){
        return diagnosisService.findAllDiagnosisForUser(user);
    }

    @PostMapping("/add")
    public Diagnosis addEvent(@AuthenticationPrincipal User user,
                              @RequestBody Diagnosis diagnosis){
        diagnosis.setUser(user);
        return diagnosisRepository.save(diagnosis);
    }

    @DeleteMapping("{id}")
    public void deleteDiagnosis(@PathVariable("id") Diagnosis diagnosis){
        diagnosisRepository.delete(diagnosis);
    }
}
