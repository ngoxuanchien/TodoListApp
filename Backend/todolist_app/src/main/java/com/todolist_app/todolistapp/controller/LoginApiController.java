package com.todolist_app.todolistapp.controller;

import com.todolist_app.todolistapp.model.Login;
import com.todolist_app.todolistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class LoginApiController {

    @Autowired
    UserService userService;

    @PostMapping("user/login")
    public ResponseEntity loginUser(@RequestBody Login login) {
        return null;
    }
}
