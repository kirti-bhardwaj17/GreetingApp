package com.example.greeting.Services;

import com.example.greeting.dto.AuthUserDTO;
import com.example.greeting.dto.LoginDTO;
import com.example.greeting.model.AuthUser;
import com.example.greeting.repository.AuthUserRepository;
import com.example.greeting.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthUserService(AuthUserRepository authUserRepository, JwtUtil jwtUtil) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public String registerUser(AuthUserDTO userDTO) {
        if (authUserRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        AuthUser user = new AuthUser();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        authUserRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(LoginDTO loginDTO) {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(loginDTO.getEmail());

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found!");
        }

        AuthUser user = userOptional.get();
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password!");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return token;
    }
}
