package com.example.hexarch.controller;

import com.example.hexarch.entity.UserEntity;
import com.example.hexarch.layered.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    private final UserService userService;

    @GetMapping("test/user")
    public List<UserEntity> findUser() {
        return userService.findMemUsers();
    }

    public TestController(UserService userService) {
        this.userService = userService;
    }
}
