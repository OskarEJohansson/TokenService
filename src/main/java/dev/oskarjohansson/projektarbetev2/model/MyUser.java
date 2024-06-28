package dev.oskarjohansson.projektarbetev2.model;


import jakarta.validation.constraints.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Service;


@Document
public record MyUser(@MongoId String id, @NotBlank(message = "Username must not be empty") @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters") String username, @NotBlank(message = "Password must not be blank") @Size(min = 6, max= 30, message = "Password must be between 6 and 30 characters") String password, Roles role){
}
