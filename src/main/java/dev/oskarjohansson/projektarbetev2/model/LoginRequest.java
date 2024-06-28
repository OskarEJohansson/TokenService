package dev.oskarjohansson.projektarbetev2.model;


import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank(message = "Username must not be empty") String username, @NotBlank(message = "Password must not be empty") String password) {
}
