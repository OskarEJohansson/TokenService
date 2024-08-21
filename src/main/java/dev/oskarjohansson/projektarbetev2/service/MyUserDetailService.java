package dev.oskarjohansson.projektarbetev2.service;

import dev.oskarjohansson.projektarbetev2.model.MyUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface MyUserDetailService extends UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public ResponseEntity<?> saveUser(MyUser user, boolean consent);

    public List<MyUser> getUsers();



}
