package com.example.MedSys.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, DOCTOR, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
