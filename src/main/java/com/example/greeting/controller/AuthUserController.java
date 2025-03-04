package com.example.greeting.controller;

import com.example.greeting.dto.AuthUserDTO;
import com.example.greeting.dto.LoginDTO;
import com.example.greeting.Services.AuthUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    private final AuthUserService authUserService;

    public AuthUserController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO userDTO) {
        String response = authUserService.registerUser(userDTO);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        String token = authUserService.loginUser(loginDTO);
        return ResponseEntity.ok().body("{\"message\": \"Login successful!\", \"token\": \"" + token + "\"}");
    }
}
