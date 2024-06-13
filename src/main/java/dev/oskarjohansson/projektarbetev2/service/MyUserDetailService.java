package dev.oskarjohansson.projektarbetev2.service;

import com.mongodb.DuplicateKeyException;
import dev.oskarjohansson.projektarbetev2.configuration.SecurityConfiguration;
import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final static Logger LOG = LoggerFactory.getLogger(MyUserDetailService.class);

    private UserRepository userRepository;
    private SecurityConfiguration securityConfiguration;

    public MyUserDetailService(UserRepository userRepository, SecurityConfiguration securityConfiguration){
        this.userRepository = userRepository;
        this.securityConfiguration = securityConfiguration;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<MyUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            GrantedAuthority authority = new SimpleGrantedAuthority(userObj.role().getAuthority());
            LOG.debug(authority.getAuthority());
            return User.builder()
                    .username(userObj.username())
                    .password(userObj.password())
                    .roles(String.valueOf(authority))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public ResponseEntity<?> saveUser(MyUser user) {
        try {
            String encodedPassword = securityConfiguration.passwordEncoder().encode(user.password());
            MyUser newUser = new MyUser(null, user.username(), encodedPassword, user.role());

            return ResponseEntity.ok(userRepository.save(newUser));

        } catch (DuplicateKeyException e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getCause());
        } catch (Exception ex) {
            ex.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getCause());
        }

    }

    public List<MyUser> getUsers(){
        return userRepository.findAll();
    }



}
