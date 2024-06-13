package dev.oskarjohansson.projektarbetev2.model;

import org.springframework.validation.annotation.Validated;

public record LoginRequest(String username, String password) {
}
