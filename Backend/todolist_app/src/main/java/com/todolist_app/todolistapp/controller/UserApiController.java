package com.todolist_app.todolistapp.controller;

import com.todolist_app.todolistapp.model.DTO.UserDTO;
import com.todolist_app.todolistapp.model.Enum.RegisterStatus;
import com.todolist_app.todolistapp.model.DTO.Login;
import com.todolist_app.todolistapp.model.Entity.User;
import com.todolist_app.todolistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
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

//    @PostMapping("/login")
//    public ResponseEntity loginUser(@RequestBody Login login) {
//        System.out.println(login.getEmail());
//        // Get User Email:
//        UserDTO userDTO = userService.checkUserEmail(login.getEmail());
//
//        // Check If Email Is Empty:
//        if (userDTO == null) {
//            return new ResponseEntity<>("Email does not exist", HttpStatus.NOT_FOUND);
//        }
//        // End Of Check Email Is Empty.
//
//        // Get Hashed User Password:
//        String hashed_password = userDTO.getPassword();
//
//        // Validate Get User Password:
//        if (!BCrypt.checkpw(login.getPassword(), hashed_password)) {
//            return  new ResponseEntity<>("Incorrect email or password", HttpStatus.BAD_REQUEST);
//        }
//
//        // Set User Object:
//        return new ResponseEntity<>(userDTO, HttpStatus.OK);
//    }


}
