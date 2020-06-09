package com.example.MedSys.dto;

import com.example.MedSys.domain.Role;
import com.example.MedSys.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {

    private boolean ADMIN = false;

    private boolean DOCTOR = false;

    public UserRoleDto(User user){
        Set<Role> roles = user.getRoles();

        for(Role role : roles){
           if(role.name().equals("ADMIN")){
               ADMIN = true;

           }
            if(role.name().equals("DOCTOR")){
                DOCTOR = true;
            }
        }
    }
}
