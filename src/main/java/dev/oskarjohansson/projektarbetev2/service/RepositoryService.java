package dev.oskarjohansson.projektarbetev2.service;

import dev.oskarjohansson.projektarbetev2.model.MyUser;

import java.util.List;
import java.util.Optional;

public interface RepositoryService {

    public Optional<MyUser> getUserByUsername(String username);

    public MyUser saveUser(MyUser user);

    public List<MyUser> getAllUsers();

}
