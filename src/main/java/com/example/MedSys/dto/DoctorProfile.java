package com.example.MedSys.dto;

import com.example.MedSys.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfile {

    private String username;
    private String email;

    private String firstName;
    private String lastName;
    private String age;
    private String phoneNumber;

    private String position;
    private String experience;

    public DoctorProfile(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.phoneNumber = user.getPhoneNumber();
        this.position = user.getPosition();
        this.experience = user.getExperience();
    }

}
