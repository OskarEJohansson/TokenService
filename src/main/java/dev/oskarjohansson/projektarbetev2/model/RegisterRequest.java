package dev.oskarjohansson.projektarbetev2.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record RegisterRequest(@NotBlank @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters") String username,@Size(min = 6, max= 30, message = "Password must be between 6 and 30 characters") @NotBlank String password, String role, @NotNull Boolean consent) {
}
