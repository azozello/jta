package com.jta.shop.service.interfaces;

import com.jta.shop.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO: Update method
 * @author azozello
 * @since  03.07.17.
 */

@Service
public interface ItemService {

    List<Item> getAll();

    List<Item> getAllByType(String type);

    Item getItemByName(String name);

    Item getItemById(int id);

    void saveItem(Item item);

    void deleteItem(int id);

    void updateItem(Item item);

    void deleteAll();
}
