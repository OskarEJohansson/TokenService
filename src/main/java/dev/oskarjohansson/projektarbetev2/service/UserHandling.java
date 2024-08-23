package dev.oskarjohansson.projektarbetev2.service;

import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.model.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserHandling {
    UserDetails createUserDetailsAndGrantAuthority(Optional<MyUser> user);

    MyUser createNewUser(RegisterRequest request);
}
