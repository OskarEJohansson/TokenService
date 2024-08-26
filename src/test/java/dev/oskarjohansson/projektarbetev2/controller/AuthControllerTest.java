package dev.oskarjohansson.projektarbetev2.controller;

import dev.oskarjohansson.projektarbetev2.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TokenService.class)
@Import(SecurityException.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtEncoder jwtEncoder;

    @MockBean
    private JwtDecoder jwtDecoder;

    @BeforeEach
    void setUp() {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject("USER")
                .claim("scope", "ROLE_USER")
                .build();

        Jwt jwt = new Jwt("tokenValue", Instant.now(), Instant.now().plusSeconds(3600)
        , Map.of("alg", "none"), claims.getClaims()); {
        }

        when(jwtEncoder.encode(any())).thenReturn(jwt);
        when(jwtDecoder.decode(anyString())).thenReturn(jwt);

    }

    @Test
    void testFailToAccessTokenWithoutCredentials() throws Exception {
        mvc.perform(post("/token")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "USER", password = "123456", roles = "USER")
    void testAccessTokenWithCredentials() throws Exception {

    }
}
