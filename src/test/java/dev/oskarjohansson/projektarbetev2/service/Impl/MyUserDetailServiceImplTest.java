package dev.oskarjohansson.projektarbetev2.service.Impl;

import dev.oskarjohansson.projektarbetev2.model.RegisterRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MyUserDetailServiceImplTest {

    @Autowired
    private MyUserDetailServiceImpl myUserDetailService;

    @Test
    void loadUserByUsername() {
    }


    @Test
    void saveUserAndGetDuplicateKeyException() throws Exception {

        RegisterRequest request1 = new RegisterRequest.Builder()
                .userName("oskar")
                .password("123456")
                .role(null)
                .consent(true)
                .build();


        Exception exception = Assertions.assertThrows(DuplicateKeyException.class, () -> {
            myUserDetailService.saveUser(request1);
        });
        System.out.println("SOUT ->>>>>>>> " + exception.getMessage());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key error"));
    }


}