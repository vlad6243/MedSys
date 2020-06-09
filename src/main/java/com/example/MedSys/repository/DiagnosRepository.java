package com.example.MedSys.repository;

import com.example.MedSys.domain.Diagnos;
import com.example.MedSys.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosRepository extends JpaRepository<Diagnos, Long> {
    List<Diagnos> findByUserOrderByIdDesc(User user);
}
