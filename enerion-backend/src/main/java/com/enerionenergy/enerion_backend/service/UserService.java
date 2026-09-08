package com.enerionenergy.enerion_backend.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.enerionenergy.enerion_backend.entity.User;
import com.enerionenergy.enerion_backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));   // username : testuser,  password : abc123
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get one user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Create a user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Delete a user
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }
}
