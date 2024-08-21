package dev.oskarjohansson.projektarbetev2.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RegisterRequest(@NotBlank String username, @NotBlank String password, String role, @NotNull Boolean consent) {
}
