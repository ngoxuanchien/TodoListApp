package com.todolist_app.todolistapp.controller;

import com.todolist_app.todolistapp.model.auth.RegisterRequest;
import com.todolist_app.todolistapp.model.auth.UserResponse;
import com.todolist_app.todolistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> profileUser() {
        return ResponseEntity.ok(userService.profileUser());
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(
            @RequestBody RegisterRequest newUser
            ) {
        return ResponseEntity.ok(userService.updateUser(newUser));
    }


}
