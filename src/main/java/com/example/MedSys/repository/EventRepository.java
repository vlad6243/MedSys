package com.example.MedSys.repository;

import com.example.MedSys.domain.Event;
import com.example.MedSys.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUser(User user);
    List<Event> findByDoctorUsernameOrderByIdDesc(String doctorUsername);
}
