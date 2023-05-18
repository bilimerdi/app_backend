package com.example.app.service;

import com.example.app.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getUsers();
    User getUser(Long id);
    User updateUser(Long id,User user);
}
