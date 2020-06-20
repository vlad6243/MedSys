package com.example.MedSys.controller;

import com.example.MedSys.domain.User;
import com.example.MedSys.dto.DoctorProfile;
import com.example.MedSys.dto.UserProfile;
import com.example.MedSys.dto.UserRoleDto;
import com.example.MedSys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public UserProfile getProfile(@AuthenticationPrincipal User user){
        return new UserProfile(user);
    }

    @GetMapping("/doctor")
    public DoctorProfile getDoctorProfile(@AuthenticationPrincipal User user){
        return new DoctorProfile(user);
    }

    @GetMapping("/doctors")
    public List<User> getRoleProfile(){
        return userService.getRoleUser();
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateProfile(@AuthenticationPrincipal User currentUser,
                                           @RequestBody UserProfile userProfile){
        try {
            userService.updateUser(userProfile,currentUser);

            Map<Object, Object> response = new HashMap<>();
            response.put("profile", userProfile);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad inputs value");
        }

    }

    @PostMapping("/`updateDoctor`")
    public ResponseEntity<?> updateDoctorProfile(@AuthenticationPrincipal User currentUser,
                                           @RequestBody DoctorProfile doctorProfile){
        try {
            userService.updateDoctor(doctorProfile,currentUser);

            Map<Object, Object> response = new HashMap<>();
            response.put("profile", doctorProfile);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad inputs value");
        }

    }

    @GetMapping("/allUsers")
    public List<User> getAllUser(){
        return userService.findAll();
    }

    @PostMapping("/changeRoleAdmin")
    public void changeRoleAdmin(@RequestBody UserProfile userProfile){
        userService.changeRoleAdmin(userProfile);
    }

    @PostMapping("/changeRoleDoctor")
    public void changeRoleDoctor(@RequestBody UserProfile userProfile){
        userService.changeRoleDoctor(userProfile);
    }

    @GetMapping("/roles")
    public UserRoleDto getRoles(@AuthenticationPrincipal User user){
        return new UserRoleDto(user);
    }
}
