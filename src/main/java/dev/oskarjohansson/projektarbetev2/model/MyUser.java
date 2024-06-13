package dev.oskarjohansson.projektarbetev2.model;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public record MyUser(@MongoId String id, String username, String password, Roles role){
}
