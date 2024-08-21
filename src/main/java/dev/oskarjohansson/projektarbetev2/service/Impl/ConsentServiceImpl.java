package dev.oskarjohansson.projektarbetev2.service.Impl;

import dev.oskarjohansson.projektarbetev2.model.MyUser;
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
    public UserConsent consentToTermsAndAgreement(Boolean consent, MyUser user) {
        UserConsent userConsent = new UserConsent(consent, Instant.now());

        if(userConsent != null){
            LOG.info("""
                    User id: {}
                    User consent: {},
                    timestamp: {}
                    """, user.id(), userConsent.isTermsAndAgreementsConsented(), userConsent.timestamp() );
        }
        else LOG.warn("UserConsent object is null");

        return  userConsent;
    }
}
