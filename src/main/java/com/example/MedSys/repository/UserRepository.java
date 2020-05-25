package com.example.MedSys.repository;

import com.example.MedSys.domain.Role;
import com.example.MedSys.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    List<User> findByRoles(Role role);
}
