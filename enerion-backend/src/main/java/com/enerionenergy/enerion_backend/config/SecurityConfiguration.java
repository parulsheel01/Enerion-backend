package com.enerionenergy.enerion_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.enerionenergy.enerion_backend.service.UserDetailsServiceImplementation;

@Configuration
public class SecurityConfiguration {
    @Autowired
    private UserDetailsServiceImplementation userDetailsServiceImpl;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> {})
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/signup").permitAll()
                .requestMatchers("/api/users/login").permitAll()

                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/api/bikes/admin/**").hasRole("ADMIN")
                
                // GET all users → ADMIN ONLY
                .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                // Individual user → authentication required
                .requestMatchers(HttpMethod.GET, "/api/users/**").authenticated()

                .requestMatchers("/api/favourites/**","/api/users/**").authenticated()
                .requestMatchers("/api/bikes/**").permitAll()
                .anyRequest().permitAll()
            )
            //.httpBasic(basic -> basic.disable())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> 
            session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        )
        .csrf(csrf -> csrf.disable());
        return http.build();
    }
    
    public void configure(AuthenticationManagerBuilder config) throws Exception{
        config.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Bean
    public org.springframework.security.authentication.AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}