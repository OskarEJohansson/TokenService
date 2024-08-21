package dev.oskarjohansson.projektarbetev2.service.Impl;

import com.mongodb.DuplicateKeyException;
import dev.oskarjohansson.projektarbetev2.configuration.SecurityConfiguration;
import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.model.UserConsent;
import dev.oskarjohansson.projektarbetev2.service.ConsentService;
import dev.oskarjohansson.projektarbetev2.service.MyUserDetailService;
import dev.oskarjohansson.projektarbetev2.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MyUserDetailServiceImpl implements MyUserDetailService {

    private final static Logger LOG = LoggerFactory.getLogger(dev.oskarjohansson.projektarbetev2.service.Impl.MyUserDetailServiceImpl.class);

    private final RepositoryService repositoryService;
    private final SecurityConfiguration securityConfiguration;
    private final ConsentService consentService;

    public MyUserDetailServiceImpl(RepositoryService repositoryService, SecurityConfiguration securityConfiguration, ConsentService consentService){
        this.repositoryService = repositoryService;
        this.securityConfiguration = securityConfiguration;
        this.consentService = consentService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LOG.debug("Logging username when loading user: {}", username);
        Optional<MyUser> user = repositoryService.getUserByUsername(username);
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

    public ResponseEntity<?> saveUser(MyUser user, boolean consent) {
        try {
            String encodedPassword = securityConfiguration.passwordEncoder().encode(user.password());

            UserConsent userConsent = consentService.consentToTermsAndAgreement(consent, user);

            MyUser newUser = new MyUser(null, user.username(), encodedPassword, user.role(), userConsent);

            LOG.info("New user saved with email address: {}", user.username());
            return ResponseEntity.ok(repositoryService.saveUser(newUser));

        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            LOG.error("Duplicate key exception", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getCause());
        } catch (Exception ex) {
            ex.printStackTrace();

            LOG.error("An error occurred", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getCause());
        }

    }

    public List<MyUser> getUsers(){
        return repositoryService.getAllUsers();
    }



}
