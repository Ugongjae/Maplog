package com.maplog.b.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/home")
public class HomeContoller {
    @GetMapping(value="/block")
    public void getAllBlocks(){

    }
}
