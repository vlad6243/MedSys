package com.example.MedSys.service;

import com.example.MedSys.domain.Role;
import com.example.MedSys.domain.User;
import com.example.MedSys.dto.DoctorProfile;
import com.example.MedSys.dto.UserForm;
import com.example.MedSys.dto.UserProfile;
import com.example.MedSys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean create(UserForm userInputs){
        User newUser = User.builder()
                .username(userInputs.getUsername())
                .email(userInputs.getEmail())
                .password(passwordEncoder.encode(userInputs.getPassword()))
                .active(true)
                .roles(Collections.singleton(Role.USER))
                .build();
        userRepository.save(newUser);

        return true;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void updateUser(UserProfile userUpdate, User currentUser){
        if(!userUpdate.getEmail().isEmpty()){
            currentUser.setEmail(userUpdate.getEmail());
        }

        if(!userUpdate.getFirstName().isEmpty()){
            currentUser.setFirstName(userUpdate.getFirstName());
        }

        if(!userUpdate.getLastName().isEmpty()){
            currentUser.setLastName(userUpdate.getLastName());
        }

        if(!userUpdate.getAge().isEmpty()){
            currentUser.setAge(userUpdate.getAge());
        }

        if (!userUpdate.getPhoneNumber().isEmpty()){
            currentUser.setPhoneNumber(userUpdate.getPhoneNumber());
        }

        userRepository.save(currentUser);
    }

    public void updateDoctor(DoctorProfile doctorProfile, User currentUser){

        if(!doctorProfile.getPosition().isEmpty()){
            currentUser.setPosition(doctorProfile.getPosition());
        }

        if(!doctorProfile.getExperience().isEmpty()){
            currentUser.setExperience(doctorProfile.getExperience());
        }
        userRepository.save(currentUser);
    }

    public void updateRole(User user,  Map<String, String> form) {

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);
    }

    public void changeRoleAdmin(UserProfile userProfile){

        User user = userRepository.findByUsername(userProfile.getUsername());

        Set<Role> roles = user.getRoles();

        boolean admin = false;
        for(Role role : roles){
            if(role.name().equals("ADMIN")){
                user.getRoles().remove(Role.ADMIN);
                admin = false;
                break;
            }else {
                admin = true;
            }
        }
        if (admin){
            user.getRoles().add(Role.ADMIN);
        }else{
            user.getRoles().add(Role.USER);
        }
        userRepository.save(user);
    }

    public void changeRoleDoctor(UserProfile userProfile){

        User user = userRepository.findByUsername(userProfile.getUsername());

        Set<Role> roles = user.getRoles();

        boolean doctor = false;
        for(Role role : roles){
            if(role.name().equals("DOCTOR")){
                user.getRoles().clear();
                doctor = false;
                break;
            }else {
                doctor = true;
            }
        }
        if (doctor){
            user.getRoles().add(Role.DOCTOR);
        }else{
            user.getRoles().add(Role.USER);
        }

        userRepository.save(user);
    }

    public List<User> getRoleUser(){
        return userRepository.findByRoles(Role.DOCTOR);
    }

    public void userDelete(Long userId){
        User user = userRepository.findById(userId).get();

        user.setActive(false);
        userRepository.save(user);
    }
}
