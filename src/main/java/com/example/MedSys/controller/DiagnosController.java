package com.example.MedSys.controller;

import com.example.MedSys.domain.Diagnos;
import com.example.MedSys.domain.User;
import com.example.MedSys.repository.DiagnosRepository;
import com.example.MedSys.service.DiagnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/diagnosis")
public class DiagnosController {

    @Autowired
    private DiagnosRepository diagnosRepository;

    @Autowired
    private DiagnosService diagnosService;

    @GetMapping
    public List<Diagnos> getAllUsersEvent(@AuthenticationPrincipal User user){
        return diagnosService.findAllDiagnosisForUser(user);
    }

    @PostMapping("/add")
    public Diagnos addEvent(@AuthenticationPrincipal User user,
                            @RequestBody Diagnos diagnos){
        diagnos.setUser(user);
        return diagnosRepository.save(diagnos);
    }

    @DeleteMapping("{id}")
    public void deleteDiagnosis(@PathVariable("id") Diagnos diagnos){
        diagnosRepository.delete(diagnos);
    }
}
