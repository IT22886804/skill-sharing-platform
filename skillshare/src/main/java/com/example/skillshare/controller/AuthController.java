package com.example.skillshare.controller;

import com.example.skillshare.dto.LoginRequest;
import com.example.skillshare.dto.RegisterRequest;
import com.example.skillshare.enums.RegistrationSource;
import com.example.skillshare.model.User;
import com.example.skillshare.service.AuthService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.skillshare.service.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/oauth2/success")
    @ResponseBody
    public ResponseEntity<Object> handleOAuth2Success(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");
            String picture = principal.getAttribute("picture");

            //create or update user
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setProfileImage(picture);
            user.setRegistrationSource(RegistrationSource.GOOGLE);

            //user service will handle if user already exists
            return userService.createUser(user);
        } else {
            return ResponseEntity.badRequest().body("OAuth authentication failed");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User user){
        return userService.loginUser(user.getEmail(), user.getPassword());
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getCurrentUser(@RequestHeader("Authorization") String token) {
        //token validation is already handled by the JwtAuthenticationFilter
        //here we just return a success message for testing
        Map<String, String> response = new HashMap<>();
        response.put("message", "Token is valid");
        return ResponseEntity.ok(response);
    }
}
