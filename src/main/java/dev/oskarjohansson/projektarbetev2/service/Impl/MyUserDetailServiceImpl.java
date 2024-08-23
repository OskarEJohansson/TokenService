package dev.oskarjohansson.projektarbetev2.service.Impl;

import com.mongodb.DuplicateKeyException;
import dev.oskarjohansson.projektarbetev2.configuration.SecurityConfiguration;
import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.model.RegisterRequest;
import dev.oskarjohansson.projektarbetev2.model.UserConsent;
import dev.oskarjohansson.projektarbetev2.service.ConsentService;
import dev.oskarjohansson.projektarbetev2.service.MyUserDetailService;
import dev.oskarjohansson.projektarbetev2.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MyUserDetailServiceImpl extends MyUserDetailServiceUtilityImpl implements MyUserDetailService {

    private final static Logger LOG = LoggerFactory.getLogger(dev.oskarjohansson.projektarbetev2.service.Impl.MyUserDetailServiceImpl.class);

    private final RepositoryService repositoryService;
    private final SecurityConfiguration securityConfiguration;

    public MyUserDetailServiceImpl(RepositoryService repositoryService, SecurityConfiguration securityConfiguration) {
        this.repositoryService = repositoryService;
        this.securityConfiguration = securityConfiguration;
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

    public MyUser saveUser(RegisterRequest request) throws Exception {

            MyUser newUser = new MyUser(
                    null,
                    request.username(),
                    securityConfiguration.passwordEncoder().encode(request.password()),
                    null,
                    consentToTermsAndAgreement(request));

            LOG.info("New user saved with email address: {}", request.username());

            return repositoryService.saveUser(newUser);
    }

    public List<MyUser> getUsers() {
        return repositoryService.getAllUsers();
    }


}
