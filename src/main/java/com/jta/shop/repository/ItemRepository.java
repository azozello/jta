package com.jta.shop.repository;

import com.jta.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author azozello
 * @since  03.07.17.
 */

public interface ItemRepository extends JpaRepository<Item, Integer> {

        List<Item> findAllByType(String type);

    Item findItemByName(String name);

    Item findItemById(int id);

    void deleteAll();
}
