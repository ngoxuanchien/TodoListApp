package com.todolist_app.todolistapp.controller;

import com.todolist_app.todolistapp.model.auth.AuthenticationRequest;
import com.todolist_app.todolistapp.model.auth.AuthenticationResponse;
import com.todolist_app.todolistapp.model.auth.RegisterRequest;
import com.todolist_app.todolistapp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    /**
     * register user
     * @param request
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.Register(request));
    }

    /**
     * authenticate user
     * @param request
     * @return
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.Authenticate(request));
    }
}
