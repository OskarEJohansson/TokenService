package dev.oskarjohansson.projektarbetev2.service.Impl;

import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.model.RegisterRequest;
import dev.oskarjohansson.projektarbetev2.model.UserConsent;
import dev.oskarjohansson.projektarbetev2.service.ConsentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ConsentServiceImpl implements ConsentService {

    private final static Logger LOG = LoggerFactory.getLogger(dev.oskarjohansson.projektarbetev2.service.Impl.ConsentServiceImpl.class);

    @Override
    public UserConsent consentToTermsAndAgreement(RegisterRequest request) {
        UserConsent userConsent = new UserConsent(request.consent(), Instant.now());

        if(userConsent != null){
            LOG.debug("""
                    User id: {}
                    User consent: {},
                    timestamp: {}
                    """, request.username(), userConsent.isTermsAndAgreementsConsented(), userConsent.timestamp() );
        }
        else LOG.warn("UserConsent object is null for user id:", request.username());

        return  userConsent;
    }
}
