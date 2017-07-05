package com.jta.shop.service.implementations;

import com.jta.shop.JtaApplication;
import com.jta.shop.entity.Item;
import com.jta.shop.entity.User;
import com.jta.shop.repository.ItemRepository;
import com.jta.shop.repository.UserRepository;
import com.jta.shop.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

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

    @Override
    public void addInCash(Item item) {
        try {
            User user = userRepository.findUserByUsername(getCurrentUserName());
            user.setData(user.getData()+"_"+item);
        } catch (Exception e){
            e.printStackTrace();
            JtaApplication.getLogger().info(e.getMessage());
        }
    }

    @Override
    public void deleteFromCash(Item item) {
        try {
            User user = userRepository.findUserByUsername(getCurrentUserName());
            String cash = user.getData();

            StringBuilder stringBuilder = new StringBuilder();
            String[] items = cash.split("_");

            for (String s : items){
                if (!s.equals(item.toString())){
                    stringBuilder.append(s);
                }
            }

            user.setData(stringBuilder.toString());
            userRepository.saveAndFlush(user);
        } catch (Exception e){
            e.printStackTrace();
            JtaApplication.getLogger().info(e.getMessage());
        }
    }

    @Override
    public void clearCash() {
        try {
            User user = userRepository.findUserByUsername(getCurrentUserName());
            user.setData("");
            userRepository.saveAndFlush(user);
        } catch (Exception e){
            e.printStackTrace();
            JtaApplication.getLogger().info(e.getMessage());
        }
    }

    @Override
    public List<Item> getItemsFromCash() {
        List<Item> itemsList = new ArrayList<>();
        try {
            User user = userRepository.findUserByUsername(getCurrentUserName());
            String cash = user.getData();
            String[] itemsCash = cash.split("_");
            for (String itemString : itemsCash){
                Item tempItem = new Item();
                String[] item = itemString.split("\\|");

                tempItem.setId(Integer.parseInt(item[0]));
                tempItem.setName(item[1]);
                tempItem.setImages(item[2]);
                tempItem.setDescription(item[3]);
                tempItem.setReports(item[4]);
                tempItem.setType(item[5]);

                itemsList.add(tempItem);
            }
        } catch (Exception e){
            e.printStackTrace();
            JtaApplication.getLogger().info(e.getMessage());
        }
        return itemsList;
    }

    private String getCurrentUserName(){
        try {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user.getUsername();
        } catch (Exception e){
            e.printStackTrace();
            JtaApplication.getLogger().info(e.getMessage());
            return "Unsigned";
        }
    }
}
