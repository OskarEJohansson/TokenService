package dev.oskarjohansson.projektarbetev2.service.Impl;

import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.service.UserHandling;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailServiceUtility implements UserHandling {

    @Override
    public UserDetails createUserDetailsAndGrantAuthority(Optional<MyUser> user) throws UsernameNotFoundException {
        var userObj = user.get();
        return User.builder()
                .username(userObj.username())
                .password(userObj.password())
                .authorities(userObj.role().name())
                .build();
    }
}
