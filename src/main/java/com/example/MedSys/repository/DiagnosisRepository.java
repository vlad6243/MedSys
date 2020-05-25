package com.example.MedSys.repository;

import com.example.MedSys.domain.Diagnosis;
import com.example.MedSys.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    List<Diagnosis> findByUser(User user);
}
