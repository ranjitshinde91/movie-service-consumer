package com.controller;

import com.service.HelloWordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

    private HelloWordService helloWordService;

    public HelloWordController(HelloWordService helloWordService) {
        this.helloWordService = helloWordService;
    }

    @RequestMapping("/")
    public String helloWord(){
        return helloWordService.message("DDLJ");
    }
}
