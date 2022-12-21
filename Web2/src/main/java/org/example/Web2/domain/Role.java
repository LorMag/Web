package org.example.Web2.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    BANNED;

    @Override
    public String getAuthority() {
        return name();
    }
}