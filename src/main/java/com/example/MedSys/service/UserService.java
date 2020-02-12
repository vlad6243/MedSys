package com.example.MedSys.service;

import com.example.MedSys.domain.Role;
import com.example.MedSys.domain.User;
import com.example.MedSys.dto.UserForm;
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

    public User create(UserForm userInputs){
        User newUser = User.builder()
                .username(userInputs.getUsername())
                .email(userInputs.getEmail())
                .password(passwordEncoder.encode(userInputs.getPassword()))
                .active(true)
                .roles(Collections.singleton(Role.USER))
                .build();

        return userRepository.save(newUser);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User updateUser(UserForm userUpdate, User currentUser){
        if (!userUpdate.getEmail().isBlank()){
            currentUser.setEmail(userUpdate.getEmail());
        }

        if (!userUpdate.getPassword().isBlank()){
            currentUser.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
        }

        if(!userUpdate.getUsername().isBlank()){
            currentUser.setUsername(userUpdate.getUsername());
        }

        return userRepository.save(currentUser);
    }

    public void updateRole(User user, String username, Map<String, String> form) {
        user.setUsername(username);

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

    public void userDelete(Long userId){
        User user = userRepository.findById(userId).get();

        user.setActive(false);
        userRepository.save(user);
    }
}
