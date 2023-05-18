package com.example.app.service.impl;

import com.example.app.repository.UseRepository;
import com.example.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UseRepository useRepository;

}
