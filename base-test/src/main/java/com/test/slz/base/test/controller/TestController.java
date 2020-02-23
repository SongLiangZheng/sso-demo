package com.test.slz.base.test.controller;

import com.test.slz.base.test.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(String address, User user,int id1,Integer id2){
        return "hello";
    }

}
