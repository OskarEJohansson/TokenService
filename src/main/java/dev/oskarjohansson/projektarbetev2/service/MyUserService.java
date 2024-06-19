package dev.oskarjohansson.projektarbetev2.service;

import dev.oskarjohansson.projektarbetev2.configuration.SecurityConfiguration;
import dev.oskarjohansson.projektarbetev2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {

    private final static Logger LOG = LoggerFactory.getLogger(MyUserDetailService.class);

    private UserRepository userRepository;
    private SecurityConfiguration securityConfiguration;

    public MyUserService(UserRepository userRepository, SecurityConfiguration securityConfiguration){
        this.userRepository = userRepository;
        this.securityConfiguration = securityConfiguration;

    }

}
