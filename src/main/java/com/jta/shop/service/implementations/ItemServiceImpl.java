package com.jta.shop.service.implementations;

import com.jta.shop.JtaApplication;
import com.jta.shop.entity.Item;
import com.jta.shop.repository.ItemRepository;
import com.jta.shop.service.interfaces.ItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * @author azozello
 * @since  03.07.17.
 */

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAll() {
        try {
            return itemRepository.findAll();
        } catch (Exception e){
            JtaApplication.getLogger().error(e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Item> getAllByType(String type) {
        try {
            return itemRepository.findAllByType(type);
        } catch (Exception e){
            JtaApplication.getLogger().error(e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Item getItemByName(String name) {
        try {
            return itemRepository.findItemByName(name);
        } catch (Exception e){
            JtaApplication.getLogger().error(e.getMessage());
            e.printStackTrace();
            return new Item("Exception","Error");
        }
    }

    @Override
    public Item getItemById(int id) {
        try {
            return itemRepository.findItemById(id);
        } catch (Exception e){
            JtaApplication.getLogger().error(e.getMessage());
            e.printStackTrace();
            return new Item("Exception","Error");
        }
    }

    @Override
    public void saveItem(Item item) {
        try {
            Formatter formatter = new Formatter();
            formatter.format("Added   item %-5d Name: %-25s Type: %-25s", item.getId(), item.getName(), item.getType());

            JtaApplication.getLogger().info(formatter.toString());
            itemRepository.save(item);
        } catch (Exception e){
            JtaApplication.getLogger().error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(int id) {
        try {
            Formatter formatter = new Formatter();
            formatter.format("Added   item %-5d", id);

            JtaApplication.getLogger().info(formatter.toString());
            itemRepository.delete(id);
        } catch (Exception e){
            JtaApplication.getLogger().error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void updateItem(Item item) {
        try {
            Formatter formatter = new Formatter();
            formatter.format("Updated item %-5d Name: %-25s Type: %-25s", item.getId(), item.getName(), item.getType());
            JtaApplication.getLogger().info(formatter.toString());

            Item updatingItem = itemRepository.findOne(item.getId());

            updatingItem.setName(item.getName());
            updatingItem.setType(item.getType());
            updatingItem.setReports(item.getReports());
            updatingItem.setImages(item.getImages());
            updatingItem.setDescription(item.getDescription());

            itemRepository.saveAndFlush(updatingItem);
        } catch (Exception e){
            JtaApplication.getLogger().error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try {
            JtaApplication.getLogger().info("Table 'items' cleared");
            itemRepository.deleteAll();
        } catch (Exception e){
            JtaApplication.getLogger().error(e.getMessage());
            e.printStackTrace();
        }
    }
}
