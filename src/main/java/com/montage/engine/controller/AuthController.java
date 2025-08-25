package com.montage.engine.controller;

import com.montage.engine.model.User;
import com.montage.engine.service.UserService;
import com.montage.engine.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody RegisterRequest request) {
        try {
            User user = userService.signUp(request.getUsername(), request.getPassword());
            // Do not send password back
            return ResponseEntity.ok(new UserResponse(user.getId(), user.getUsername()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public static class RegisterRequest {
        private String username;
        private String password;
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class UserResponse {
        private String id;
        private String username;
        public UserResponse(String id, String username) {
            this.id = id;
            this.username = username;
        }
        public String getId() { return id; }
        public String getUsername() { return username; }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return userService.login(request.getUsername(), request.getPassword())
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getUsername());
                    return ResponseEntity.ok(new AuthResponse(token));
                })
                .orElseGet(() -> ResponseEntity.status(401).build());
    }

    public static class LoginRequest {
        private String username;
        private String password;
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class AuthResponse {
        private String token;
        public AuthResponse(String token) { this.token = token; }
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }
}
