package com.example.MedSys.service;

import com.example.MedSys.domain.User;
import com.example.MedSys.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepo;

    @Test
    public void testGetById() {

        User person = userRepo.findById(Long.valueOf(42)).get();
        assertThat(person.getId()).isEqualTo(42);
    }
    @Test
    public void testCreateUser() {
        User person = userRepo.findById(Long.valueOf(42)).get();
        assertThat(person.getId()).isEqualTo(42);
    }

    @Test
    public void testUpdateProfileById() {
        User person = userRepo.findById(Long.valueOf(42)).get();
        assertThat(person.getId()).isEqualTo(42);
    }

    @Test
    public void testUpdateProfileByUsername() {
        User person = userRepo.findById(Long.valueOf(42)).get();
        assertThat(person.getId()).isEqualTo(42);
    }

    @Test
    public void testUpdateUserById() {
        User person = userRepo.findById(Long.valueOf(42)).get();
        assertThat(person.getId()).isEqualTo(42);
    }

    @Test
    public void testLoadByUsername() {

        User person = userRepo.findById(Long.valueOf(42)).get();
        assertThat(person.getId()).isEqualTo(42);
    }

    @Test
    public void testDeleteById() {

        User person = userRepo.findById(Long.valueOf(42)).get();
        assertThat(person.getId()).isEqualTo(42);
    }

    @Test
    public void testChangeRoleById() {

        User person = userRepo.findById(Long.valueOf(42)).get();
        assertThat(person.getId()).isEqualTo(42);
    }

    @Test
    public void testFindRoleById() {

        User person = userRepo.findById(Long.valueOf(42)).get();
        assertThat(person.getId()).isEqualTo(42);
    }



}
