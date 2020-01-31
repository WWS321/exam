package com.example.jackson.controller;

import com.example.jackson.NormalTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @GetMapping
    public String getCar(){
        return NormalTest.getCar();
    }
}
