package dev.oskarjohansson.projektarbetev2.service;

import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.model.RegisterRequest;
import dev.oskarjohansson.projektarbetev2.model.UserConsent;
import org.springframework.stereotype.Service;


public interface ConsentService {

    public UserConsent consentToTermsAndAgreement(RegisterRequest request);
}
