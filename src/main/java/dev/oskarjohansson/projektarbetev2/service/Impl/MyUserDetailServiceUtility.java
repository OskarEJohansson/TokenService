package dev.oskarjohansson.projektarbetev2.service.Impl;

import dev.oskarjohansson.projektarbetev2.configuration.SecurityConfiguration;
import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.model.RegisterRequest;
import dev.oskarjohansson.projektarbetev2.model.UserConsent;
import dev.oskarjohansson.projektarbetev2.service.ConsentService;
import dev.oskarjohansson.projektarbetev2.service.UserHandling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.Instant;
import java.util.Optional;

public abstract class MyUserDetailServiceUtility implements ConsentService, UserHandling {

    private final static Logger LOG = LoggerFactory.getLogger(MyUserDetailServiceUtility.class);
    private final SecurityConfiguration securityConfiguration;

    protected MyUserDetailServiceUtility(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    @Override
    public UserConsent consentToTermsAndAgreement(RegisterRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (request.consent() != null) {
            UserConsent userConsent = new UserConsent(request.consent(), Instant.now());
            userConsentLogger(userConsent, request.username());
            return userConsent;

        } else throw new IllegalArgumentException("Consent must be given");
    }

    private void userConsentLogger(UserConsent userConsent, String userName) {
        if (userConsent.isTermsAndAgreementsConsented() != null) {
            LOG.debug("""
                    User id: {}
                    User consent: {},
                    timestamp: {}
                    """, userName, userConsent, userConsent.timestamp());

        } else LOG.warn("UserConsent object is null for user id:", userName);
    }

    @Override
    public UserDetails createUserDetailsAndGrantAuthority(Optional<MyUser> user) throws UsernameNotFoundException {
        var userObj = user.get();
        return User.builder()
                .username(userObj.username())
                .password(userObj.password())
                .roles(String.valueOf(new SimpleGrantedAuthority(userObj.role().getAuthority())))
                .build();
    }

    @Override
    public MyUser createNewUser(RegisterRequest request) throws UsernameNotFoundException {
        return new MyUser(null,
                request.username(),
                securityConfiguration.passwordEncoder().encode(request.password()),
                null,
                consentToTermsAndAgreement(request)
        );
    }
}
