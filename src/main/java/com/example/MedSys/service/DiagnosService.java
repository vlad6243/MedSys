package com.example.MedSys.service;

import com.example.MedSys.domain.Diagnos;
import com.example.MedSys.domain.User;
import com.example.MedSys.repository.DiagnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosService {

    @Autowired
    private DiagnosRepository diagnosRepository;

    public List<Diagnos> findAllDiagnosisForUser(User user){
        return diagnosRepository.findByUserOrderByIdDesc(user);
    }
}
