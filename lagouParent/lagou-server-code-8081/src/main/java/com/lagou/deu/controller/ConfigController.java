package com.lagou.deu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config")
public class ConfigController {
    @Value("${test.test1}")
    String testStr;


    @RequestMapping("test")
    public String getTestStr(){
        return testStr;
    }
}
