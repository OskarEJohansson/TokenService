package dev.oskarjohansson.projektarbetev2.service.Impl;

import dev.oskarjohansson.projektarbetev2.configuration.SecurityConfiguration;
import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.service.MyUserDetailService;
import dev.oskarjohansson.projektarbetev2.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailServiceImpl extends MyUserDetailServiceUtility implements MyUserDetailService {

    private final static Logger LOG = LoggerFactory.getLogger(dev.oskarjohansson.projektarbetev2.service.Impl.MyUserDetailServiceImpl.class);
    private final RepositoryService repositoryService;

    public MyUserDetailServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws IllegalArgumentException {
        Optional<MyUser> user = repositoryService.getUserByUsername(username);
        return createUserDetailsAndGrantAuthority(user);
    }

}
