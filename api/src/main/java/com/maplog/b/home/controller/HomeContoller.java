package com.maplog.b.home.controller;

import com.maplog.b.home.service.BlockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeContoller {
    @Autowired
    BlockDao blockDao;

    @GetMapping(value="/block")
    public void getAllBlocks(){

    }

    @GetMapping(value="/block/sample")
    public void makeSamples(){
        String colorCode = "A0E7E5";
        for(int i=1;i<=10;i++){
            for(int j=1;j<=10;j++){
                blockDao.makeSample(i,j,colorCode);
            }
        }
    }
}
