package com.example.hexarch.service;

import com.example.hexarch.entity.UserEntity;
import com.example.hexarch.layered.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class User2Service {
    private final UserRepository userRepository;

    User2Service(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity a() {
        return userRepository.getUserById(1);
    }
}
