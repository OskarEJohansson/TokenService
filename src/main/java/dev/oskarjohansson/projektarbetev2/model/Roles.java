package dev.oskarjohansson.projektarbetev2.model;

import org.springframework.security.core.GrantedAuthority;

public record Roles(RoleType role) implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return role.name();
    }
}
