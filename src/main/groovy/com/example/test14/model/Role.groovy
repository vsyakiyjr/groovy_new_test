package com.example.test14.model

import org.springframework.security.core.GrantedAuthority

enum Role implements GrantedAuthority {
    USER;

    @Override
    String getAuthority() {
        return name()
    }
}