package src.org.service.impl;

import src.org.models.User;
import src.org.repository.UserRepository;
import src.org.service.UserService;

import java.util.Collection;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.saveUser(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void edit(User user) {
        userRepository.edit(user);
    }

    @Override
    public boolean delete(User user) {
        return userRepository.delete(user);
    }
}
