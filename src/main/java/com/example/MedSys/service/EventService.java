package com.example.MedSys.service;

import com.example.MedSys.domain.Event;
import com.example.MedSys.domain.User;
import com.example.MedSys.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAllEventsForUser(User user){
        return eventRepository.findByUser(user);
    }

    public List<Event> findAllEventsForDoctor(User user){
        return eventRepository.findByDoctorUsernameOrderByIdDesc(user.getUsername());
    }
}
