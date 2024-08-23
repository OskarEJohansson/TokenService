package dev.oskarjohansson.projektarbetev2.service;

import com.mongodb.DuplicateKeyException;
import dev.oskarjohansson.projektarbetev2.model.MyUser;

import java.util.List;
import java.util.Optional;

public interface RepositoryService {

    Optional<MyUser> getUserByUsername(String username);

    MyUser saveUser(MyUser user);

    List<MyUser> getAllUsers();

}
