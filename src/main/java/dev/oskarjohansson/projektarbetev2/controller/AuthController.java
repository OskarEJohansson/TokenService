package dev.oskarjohansson.projektarbetev2.controller;

import dev.oskarjohansson.projektarbetev2.model.LoginRequest;
import dev.oskarjohansson.projektarbetev2.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager){
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody LoginRequest userLogin) throws AuthenticationException {

        try {
            LOG.debug("User login credentials {}", userLogin);

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLogin.username(),
                            userLogin.password()));


            LOG.debug("Token requested for user '{}'", auth.getName());
            LOG.debug("Token granted {}", auth);

            String token = tokenService.generateToken(auth);
            return  ResponseEntity.ok(token);

        } catch (AuthenticationException e){
            LOG.debug("Autentication error, stack trace: {}", e.getStackTrace().toString());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
    }
}
