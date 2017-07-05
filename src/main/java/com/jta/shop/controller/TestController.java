package com.jta.shop.controller;

import com.jta.shop.JtaApplication;
import com.jta.shop.entity.Item;
import com.jta.shop.service.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/test")
    public List<Item> getAllTest(){
        return itemService.getAll();
    }
}
