package dev.oskarjohansson.projektarbetev2.service.Impl;

import com.mongodb.DuplicateKeyException;
import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.repository.UserRepository;
import dev.oskarjohansson.projektarbetev2.service.RepositoryService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    private final UserRepository userRepository;

    public RepositoryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<MyUser> getUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

}
