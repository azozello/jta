package com.jta.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author azozello
 * @since 05.07.17.
 */

@Controller
public class IndexController {

    @RequestMapping(value = "/item")
    public String item(){
        return "/pages/item.html";
    }

    @RequestMapping(value = "/")
    public String index(){
        return "pages/index.html";
    }

    @RequestMapping(value = "/next")
    public String next(){
        return "pages/next_page.html";
    }
}
