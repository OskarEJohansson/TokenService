package dev.oskarjohansson.projektarbetev2.service.Impl;

import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.model.RegisterRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MyUserDetailServiceUtilityTest {

    @Autowired
    private MyUserDetailServiceUtility myUserDetailServiceUtility;


    @Test
    void consentToTermsAndAgreement() {
    }

    @Test
    void createUserDetailsAndGrantAuthority() {
    }

    @Test
    void saveUserAndCheckPasswordEncodingSuccessful() throws Exception {

        RegisterRequest request = new RegisterRequest.Builder()
                .userName("user")
                .password("123456")
                .role(null)
                .consent(true)
                .build();

        MyUser user = myUserDetailServiceUtility.createNewUser(request);
        Assertions.assertNotEquals(user.password(),request.password());
    }

    @Test
    void saveUserAndCheckConsentSaveAndTimeStamp() throws Exception {
        RegisterRequest request = new RegisterRequest.Builder()
                .userName("user")
                .password("123456")
                .role(null)
                .consent(true)
                .build();

        MyUser user = myUserDetailServiceUtility.createNewUser(request);
        Assertions.assertEquals(user.userConsent().isTermsAndAgreementsConsented(), true);
        Assertions.assertNotNull(user.userConsent().timestamp());

    }
}