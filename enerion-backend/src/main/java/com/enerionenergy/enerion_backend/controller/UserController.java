package com.enerionenergy.enerion_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.enerionenergy.enerion_backend.dto.UserSummaryDTO;
import com.enerionenergy.enerion_backend.entity.User;
import com.enerionenergy.enerion_backend.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    // GET /api/users
    // @GetMapping // admin
    // public List<User> getAllUsers() {
    //     return userService.getAllUsers();
    // }

    @GetMapping
    public List<UserSummaryDTO> getAllUsers() {

        return userService.getAllUsers().stream().map(user -> new UserSummaryDTO(user.getName(), user.getEmail())).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSummaryDTO> getUserById(@PathVariable Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userEmail = authentication.getName();
        User authenticatedUser = userService.findByEmail(userEmail);

        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !authenticatedUser.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.getUserById(id);

        UserSummaryDTO dto = new UserSummaryDTO(user.getName(), user.getEmail());

        return ResponseEntity.ok(dto);
    }

    // POST /api/users
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
    userService.registerUser(user);
    return new ResponseEntity<>(HttpStatus.CREATED);
    }
     
    @PostMapping ("/login") //user
    public ResponseEntity<User> loginUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, request, response);

        User existingUser = userService.findByEmail(user.getEmail());
        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }

    // DELETE /api/users/1
    @DeleteMapping("/{id}") 
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
