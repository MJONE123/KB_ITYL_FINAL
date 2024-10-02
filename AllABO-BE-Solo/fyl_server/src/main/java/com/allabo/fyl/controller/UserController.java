package com.allabo.fyl.controller;

import com.allabo.fyl.dto.UserRegistrationRequest;
import com.allabo.fyl.entity.User;
import com.allabo.fyl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입 요청을 처리
    @PostMapping("/register")
    public User registerUser(@RequestBody UserRegistrationRequest request) {
        return userService.registerUser(
                request.getName(),
                request.getResidentNumber(),
                request.getPhoneNumber(),
                request.getFirebaseUid()
        );
    }
}
