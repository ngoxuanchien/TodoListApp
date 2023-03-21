package com.todolist_app.todolistapp.rest_controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TestApi {
    @GetMapping("/test")
    public String testEndPoint() {
        return "Test end point is working";
    }
}
