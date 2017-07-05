package com.jta.shop.controller;

import com.jta.shop.entity.Item;
import com.jta.shop.service.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class TestController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/test")
    public List<Item> getAllTest(){
        return itemService.getAll();
    }

    @RequestMapping(value = "/load")
    public String main(){
        return "pages/load.html";
    }
}
