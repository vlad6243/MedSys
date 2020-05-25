package com.example.MedSys.service;

import com.example.MedSys.domain.Diagnosis;
import com.example.MedSys.domain.Event;
import com.example.MedSys.domain.User;
import com.example.MedSys.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    public List<Diagnosis> findAllDiagnosisForUser(User user){
        return diagnosisRepository.findByUser(user);
    }
}
