package com.example.MedSys.dto;

import com.example.MedSys.domain.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserForm {

    private String username;

    private String email;

    private String password;

    private Set<Role> roles;
}
