package com.example.MedSys.controller;


import com.example.MedSys.domain.Event;
import com.example.MedSys.domain.User;
import com.example.MedSys.repository.EventRepository;
import com.example.MedSys.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllUsersEvent(@AuthenticationPrincipal User user){
        return eventService.findAllEventsForUser(user);
    }

    @PostMapping("/add")
    public Event addEvent(@AuthenticationPrincipal User user,
                          @RequestBody Event event){
        event.setUser(user);
        return eventRepository.save(event);
    }

    @DeleteMapping("{id}")
    public void deleteEvent(@PathVariable("id") Event event){
         eventRepository.delete(event);
    }


}
