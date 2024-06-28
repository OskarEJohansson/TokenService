package dev.oskarjohansson.projektarbetev2.service;

import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepositoryService {

    UserRepository userRepository;

    public RepositoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<MyUser> getUserFromUserRepository(String username) {
        return userRepository.findByUsername(username);
    }

    public MyUser saveUserToUserRepository(MyUser user){
        return userRepository.save(user);
    }

    public List<MyUser> getAllUsersFromUserRepository(){

        return userRepository.findAll();

    }


}
