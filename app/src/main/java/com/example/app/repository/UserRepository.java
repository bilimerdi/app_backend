package com.example.app.repository;

import com.example.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByAssistant(String assistant);
    List<User> findByTC(String TC);
    List<User> findByFullName(String fullName);
}

