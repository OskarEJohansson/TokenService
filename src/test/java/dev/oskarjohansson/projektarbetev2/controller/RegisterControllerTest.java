package dev.oskarjohansson.projektarbetev2.controller;

import dev.oskarjohansson.projektarbetev2.configuration.SecurityConfiguration;
import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.service.MyUserDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterController.class)
@Import(SecurityConfiguration.class)
@AutoConfigureWebMvc
class RegisterControllerTest {

    @Autowired
    MockMvc client;

    @MockBean
    private MyUserDetailService myUserDetailService;

    @MockBean
    private JwtEncoder jwtEncoder;
    @MockBean
    private JwtDecoder jwtDecoder;


    @BeforeEach
    void setUp() {
    }

    @Test
    void registerUserWithNonValidPasswordLength() throws Exception {
        String jsonRequest = "{\"username\":\"USER\", \"password\":\"1234\", \"consent\":\"true\"}";

        client.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)).andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    void registerUserWithNonValidUserLength() throws Exception {
        String jsonRequest = "{\"username\":\"USE\", \"password\":\"123456\", \"consent\":\"true\"}";

        MvcResult result = client.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)).andExpect(status().isBadRequest()).andReturn();
        Boolean responseUsername = result.getResponse().getContentAsString().contains("username");
        Boolean responseErrorMessage = result.getResponse().getContentAsString().contains("Username must be between 4 and 20 characters");

        Assertions.assertTrue(responseUsername);
        Assertions.assertTrue(responseErrorMessage);
    }

    @Test
    void testRegisterUserWithValidUsernameAndPassword() throws Exception {
        String jsonRequest = "{\"username\":\"User\", \"password\":\"123456\", \"consent\":\"true\"}";
        client.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)).andExpect(status().isOk());
    }

    @Test
    void testConsentToTermsBoolMissingInRequestAndExpectBadRequest() throws Exception {
        String jsonReuest = "{\"username\" : \"User\", \"password\" :\"123456\"}";
        client.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonReuest)).andExpect(status().isBadRequest());
    }

    @Test
    void testUsernameAndPasswordBlankInRequestAndExpectBadRequest400() throws Exception {
        String jsonReuest = "{\"consent\":\"true\"}";
        MvcResult result = client.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonReuest)).andExpect(status().isBadRequest()).andReturn();
        Boolean responseUsername = result.getResponse().getContentAsString().toLowerCase().contains("username");
        Boolean responsePassword = result.getResponse().getContentAsString().toLowerCase().contains("password");
        Boolean responseErrorMessage = result.getResponse().getContentAsString().toLowerCase().contains("must not be blank");

        Assertions.assertTrue(responseUsername);
        Assertions.assertTrue(responsePassword);
        Assertions.assertTrue(responseErrorMessage);


    }

    @Test
    void testConsentToTermsWithBoolInRequestAndExpectOk200() throws Exception {
        String jsonRequest = "{\"username\" : \"User\", \"password\" :\"123456\", \"consent\" :true}";
        client.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)).andExpect(status().isOk());
    }
}