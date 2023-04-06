package com.todolist_app.todolistapp.service;

import com.todolist_app.todolistapp.model.auth.UserResponse;
import com.todolist_app.todolistapp.model.Entity.User;
import com.todolist_app.todolistapp.model.auth.RegisterRequest;
import com.todolist_app.todolistapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse profileUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        return UserResponse.builder()
                .firstname(user.getFirst_name())
                .lastname(user.getLast_name())
                .email(user.getEmail())
                .build();
    }

    public UserResponse updateUser(RegisterRequest newUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        user.setFirst_name(newUser.getFirstname());
        user.setLast_name(newUser.getLastname());

        userRepository.save(user);

        return UserResponse.builder()
                .firstname(user.getFirst_name())
                .lastname(user.getLast_name())
                .email(user.getEmail())
                .build();
    }
}
