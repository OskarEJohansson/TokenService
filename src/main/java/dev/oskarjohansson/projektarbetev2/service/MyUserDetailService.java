package dev.oskarjohansson.projektarbetev2.service;

import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.model.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface MyUserDetailService extends UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

   MyUser saveUser(RegisterRequest request) throws Exception;

    List<MyUser> getUsers();

}
