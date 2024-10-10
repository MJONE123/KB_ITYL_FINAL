package com.allabo.fyl.fyl_server.controller;

import com.allabo.fyl.fyl_server.dto.UserRegistrationRequest;
import com.allabo.fyl.fyl_server.entity.User;
import com.allabo.fyl.fyl_server.service.UserService;
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
