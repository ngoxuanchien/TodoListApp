package com.todolist_app.todolistapp.service;

import com.todolist_app.todolistapp.model.auth.AuthenticationRequest;
import com.todolist_app.todolistapp.model.auth.AuthenticationResponse;
import com.todolist_app.todolistapp.model.auth.RegisterRequest;
import com.todolist_app.todolistapp.model.Entity.Role;
import com.todolist_app.todolistapp.model.Entity.User;
import com.todolist_app.todolistapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     *Check User exist
     * @param email
     */
    private void UserExist(String email) {
        var user = repository.findByEmail(email).orElseThrow();
    }

    /**
     * REgister new user
     * @param request
     * @return
     */
    public AuthenticationResponse Register(RegisterRequest request) {

        try {
            UserExist(request.getEmail());
            return AuthenticationResponse.builder()
                    .token("Email was exist")
                    .build();
        } catch (NoSuchElementException e) {
            var user = User.builder()
                    .first_name(request.getFirstname())
                    .last_name(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            repository.save(user);
            var jwtToken = jwtService.GenerateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
    }

    public AuthenticationResponse Authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.GenerateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
