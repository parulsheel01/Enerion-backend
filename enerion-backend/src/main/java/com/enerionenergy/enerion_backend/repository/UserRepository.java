package com.enerionenergy.enerion_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enerionenergy.enerion_backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
