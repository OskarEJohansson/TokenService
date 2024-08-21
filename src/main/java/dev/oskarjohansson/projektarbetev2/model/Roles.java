package dev.oskarjohansson.projektarbetev2.model;

import org.springframework.security.core.GrantedAuthority;

public record Roles(String role) implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return role.toUpperCase();
    }
}
