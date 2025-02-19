package com.food_delivery.identityservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food_delivery.identityservice.dto.AuthRequest;
import com.food_delivery.identityservice.model.User;
import com.food_delivery.identityservice.service.AuthenticationService;

import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return ResponseEntity.ok(authenticationService.register(user));
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        String token = authenticationService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(token);
    }
    
    @GetMapping("/allUsers")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {       
        return ResponseEntity.ok(authenticationService.getAllUsers());
    }
    
    @GetMapping("/checkUserAvailable/{userName}")
    public ResponseEntity<String> checkUserAvailable(@PathVariable String userName) {
    	return ResponseEntity.ok(authenticationService.checkUserNameAvailable(userName));
    }
    
    @GetMapping("/testJWTAuth")
    public ResponseEntity<String> testJWTAuth() {
    	return ResponseEntity.ok("Successfully authenticate the JWT token.");
    }
    
    
}
