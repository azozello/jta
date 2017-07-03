package com.jta.shop.service.implementations;

import com.jta.shop.entity.Item;
import com.jta.shop.repository.ItemRepository;
import com.jta.shop.service.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return itemRepository.findAll();
    }

    @Override
    public List<Item> getAllByType(String type) {
        return itemRepository.findAllByType(type);
    }

    @Override
    public Item getItemByName(String name) {
        return itemRepository.findItemByName(name);
    }

    @Override
    public Item getItemById(int id) {
        return itemRepository.findItemById(id);
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(int id) {
        itemRepository.delete(id);
    }

    @Override
    public void updateItem(Item item) {
        Item updatingItem = itemRepository.findOne(item.getId());

        updatingItem.setName(item.getName());
        updatingItem.setType(item.getType());
        updatingItem.setReports(item.getReports());
        updatingItem.setImages(item.getImages());
        updatingItem.setDescription(item.getDescription());

        itemRepository.saveAndFlush(updatingItem);
    }

    @Override
    public void deleteAll() {
        itemRepository.deleteAll();
    }
}
