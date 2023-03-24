package com.todolist_app.todolistapp.controller;

import com.todolist_app.todolistapp.model.Enum.RegisterStatus;
import com.todolist_app.todolistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterApiController {

    @Autowired
    UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity registerNewUser(@RequestParam("first_name") String first_name,
                                          @RequestParam("last_name") String last_name,
                                          @RequestParam("email") String email,
                                          @RequestParam("password") String password) {
        if (first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Please complete all Fields", HttpStatus.BAD_REQUEST);
        }

        // Encrypt / Hash Password:
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        RegisterStatus result = RegisterStatus.REGISTER_FAILED;
        try {
            // Register New User:
            result = userService.registerNewUserServiceMethod(first_name, last_name, email, hashed_password);
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
        } finally {
            switch (result) {
                case EMAIL_EXISTS -> {
                    return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
                }
                case REGISTER_FAILED -> {
                    return new ResponseEntity<>("Register failed", HttpStatus.BAD_REQUEST);
                }
                case REGISTER_SUCCESS -> {
                    return new ResponseEntity<>("Register success", HttpStatus.CREATED);
                }
                default -> {
                    return new ResponseEntity<>("No content", HttpStatus.NO_CONTENT);
                }
            }
        }
    }
}
