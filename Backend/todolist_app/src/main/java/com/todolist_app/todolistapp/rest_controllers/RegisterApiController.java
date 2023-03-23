package com.todolist_app.todolistapp.rest_controllers;

import com.todolist_app.todolistapp.services.UserService;
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

        int result = 0;
        try {
            // Register New User:
            result = userService.registerNewUserServiceMethod(first_name, last_name, email, hashed_password);
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
        } finally {
            if (result != 1) {
                return new ResponseEntity<>("Register failed", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("Register successed", HttpStatus.OK);
    }
}
