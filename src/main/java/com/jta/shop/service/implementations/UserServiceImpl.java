package com.jta.shop.service.implementations;

import com.jta.shop.JtaApplication;
import com.jta.shop.entity.User;
import com.jta.shop.repository.UserRepository;
import com.jta.shop.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void updateUser(User user) {
        User userInDB = userRepository.findOne(user.getId());

        user.setPassword(user.getPassword());
        user.setUsername(user.getUsername());
        user.setRole(user.getRole());

        userRepository.saveAndFlush(userInDB);
        JtaApplication.getLogger().info("Updated user: "+user.getUsername());
    }

    @Override
    public void insertUser(User user) {
        userRepository.save(user);
        JtaApplication.getLogger().info("Created user: "+user.getUsername());
    }
}
