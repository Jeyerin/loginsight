package com.back.loginsight.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JoinController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}