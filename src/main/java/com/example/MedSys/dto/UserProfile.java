package com.example.MedSys.dto;

import com.example.MedSys.domain.Role;
import com.example.MedSys.domain.User;
import lombok.*;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor
public class UserProfile {

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String age;

    private String phoneNumber;

    private Set<Role> roles;



    public UserProfile(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.phoneNumber = user.getPhoneNumber();
        this.roles = user.getRoles();
    }
}
