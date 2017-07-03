package com.jta.shop.service;

import com.jta.shop.entity.Item;
import com.jta.shop.repository.ItemRepository;

import com.jta.shop.service.interfaces.ItemService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author azozello
 * @since  03.07.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    private List<Item> productionData;
    private List<Item> testData = new ArrayList<>();

    @Before
    public void setUp(){
        productionData = itemRepository.findAll();
        itemRepository.deleteAllInBatch();

        Item item1 = new Item();
        item1.setId(1);
        item1.setName("name1");
        item1.setDescription("description1");
        item1.setImages("images1");
        item1.setReports("reports1");
        item1.setType("type1");

        Item item2 = new Item();
        item2.setId(2);
        item2.setName("name2");
        item2.setDescription("description2");
        item2.setImages("images2");
        item2.setReports("reports2");
        item2.setType("type2");

        testData.add(item1);
        testData.add(item2);

        itemRepository.save(testData);
    }

    @After
    public void tearDown(){
        itemRepository.deleteAllInBatch();
        itemRepository.save(productionData);
    }

    @Test
    public void getAllTest() {
        assertNotNull(itemService.getAll());
    }

    @Test
    public void getAllByTypeTest() {
        String type = "type1";
        List<Item> itemsByType = itemService.getAllByType(type);
        itemsByType.forEach(item -> {
            assertEquals(item.getType(),type);
        });
    }

    @Test
    public void getItemByNameTest() {
        String name = "name1";
        Item item = itemService.getItemByName(name);
        assertEquals(item.getName(),name);
    }

    @Test
    public void getItemByIdTest() {
        int id = itemRepository.findAll().get(0).getId();
        Item item = itemService.getItemById(id);
        assertEquals(item.getId(), id);
    }

    @Test
    public void saveItemTest() {
        int id = itemRepository.findAll().get(itemRepository.findAll().size()-1).getId()+1;
        Item itemToSave = new Item();
        itemToSave.setId(id);
        itemToSave.setName("Test Name");
        itemToSave.setDescription("Test Description");
        itemToSave.setImages("Test Images");
        itemToSave.setReports("Test Reports");
        itemToSave.setType("Test Type");

        itemService.saveItem(itemToSave);

        Item itemToFind = itemRepository.findOne(id);

        assertEquals(itemToSave, itemToFind);
    }

    @Test
    public void deleteItemTest() {
        int id = itemRepository.findAll().get(0).getId();
        itemService.deleteItem(id);
        assertEquals(null, itemRepository.findOne(id));
    }

    @Test
    public void updateItemTest() {
        Item testItem = itemRepository.findOne(itemRepository.findAll().get(0).getId());
        String oldName = testItem.getName();
        String newName = "New Name";
        assertNotEquals(oldName,newName);
        testItem.setName(newName);
        itemService.updateItem(testItem);
        assertEquals(itemRepository.findOne(itemRepository.findAll().get(0).getId()).getName(), newName);
    }

    @Test
    public void deleteTest(){
        assertNotNull(itemService.getAll());
        itemService.deleteAll();
        assertEquals(0,itemService.getAll().size());
    }
}
