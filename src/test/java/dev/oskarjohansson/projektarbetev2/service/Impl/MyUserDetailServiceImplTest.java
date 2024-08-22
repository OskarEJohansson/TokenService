package dev.oskarjohansson.projektarbetev2.service.Impl;

import dev.oskarjohansson.projektarbetev2.configuration.SecurityConfiguration;
import dev.oskarjohansson.projektarbetev2.model.RegisterRequest;
import dev.oskarjohansson.projektarbetev2.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MyUserDetailServiceImplTest {

    @InjectMocks
    private MyUserDetailService myUserDetailService;

    @Mock
    private ConsentService consentService;
    @Mock
    private RepositoryService repositoryService;
    @Mock
    private TokenService tokenService;
    @Mock
    private UserService userService;


    @Test
    void loadUserByUsername() {
    }

    @Test
    void saveUser() {

        RegisterRequest request = new RegisterRequest("user","123456",null, true);

    }

    @Test
    void getUsers() {

    }
}