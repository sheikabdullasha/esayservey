package com.formbuilder.easyservey.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {


    @GetMapping
    public String testContoller(){
        return "Welcome to Easy servey Home page";
    }
}
