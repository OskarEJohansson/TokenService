package dev.oskarjohansson.projektarbetev2.service.Impl;

import dev.oskarjohansson.projektarbetev2.model.RegisterRequest;
import dev.oskarjohansson.projektarbetev2.model.UserConsent;
import dev.oskarjohansson.projektarbetev2.service.ConsentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.Optional;

@Service
public class ConsentServiceImpl implements ConsentService {

    private final static Logger LOG = LoggerFactory.getLogger(dev.oskarjohansson.projektarbetev2.service.Impl.ConsentServiceImpl.class);

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
}
