package com.example.MedSys.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private LocalDateTime timeEvent;

    private Long DoctorId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
