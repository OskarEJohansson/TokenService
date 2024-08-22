package dev.oskarjohansson.projektarbetev2.service.Impl;

import dev.oskarjohansson.projektarbetev2.configuration.SecurityConfiguration;
import dev.oskarjohansson.projektarbetev2.model.RegisterRequest;
import dev.oskarjohansson.projektarbetev2.model.UserConsent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(SecurityConfiguration.class)
class ConsentServiceImplTest {

    ConsentServiceImpl consentService = new ConsentServiceImpl();

    @Test
    void consentToTermsAndAgreement() throws Exception {
        RegisterRequest request = new RegisterRequest("user","123456",null,true);
        UserConsent consent =  consentService.consentToTermsAndAgreement(request);

        assertEquals(request.username(),"user");
        assertEquals(consent.isTermsAndAgreementsConsented(), true);
        assertNotNull(consent.timestamp());

    }

    @Test
    void consentToTermsAndAgreementNullRequestAndIllegalArgumentException() throws Exception {
        RegisterRequest request = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> consentService.consentToTermsAndAgreement(request));
        assertEquals(exception.getMessage(), "Request cannot be null");
    }
}