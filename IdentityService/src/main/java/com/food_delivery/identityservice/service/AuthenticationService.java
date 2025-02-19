package com.food_delivery.identityservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.food_delivery.identityservice.model.User;
import com.food_delivery.identityservice.repository.UserRepository;
import com.food_delivery.identityservice.jwt.JwtUtil;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    public String encodePassword(String password) {
        return passwordEncoder.encode(password); // Hash the password
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword); // Verify password
    }
    
    public String register(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        userRepository.save(user);
        return user.getUsername() + " is registered successfully...";
    }
    
    public String authenticate(String username, String password) {
        User userDetails = userRepository.findByUsername(username).orElseThrow();
        if (verifyPassword(password, userDetails.getPassword())) {
        	return jwtUtil.generateToken(userDetails.getUsername());
        } else {
        	return "Invalid Access";
        }
    }
    
    public List<User> getAllUsers() {
         return userRepository.findAll();
    }
    
    public String checkUserNameAvailable(String userName) {
    	Optional<User> user = userRepository.findByUsername(userName);
        return user.isPresent() ? "Not Available" : "Available";
   }
    
    public UserDetails getUserDetails(String userName) {
    	return userDetailsService.loadUserByUsername(userName);
    }
}
