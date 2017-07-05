package com.jta.shop.service.interfaces;

import com.jta.shop.entity.Item;
import com.jta.shop.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUserById(Integer id);

    Iterable<User> getAllUsers();

    void insertUser(User user);

    void updateUser(User user);

    User getUserByUsername(String username);

    void addInCash(Item item);

    void deleteFromCash(Item item);

    void clearCash();
    
    List<Item> getItemsFromCash();
}
