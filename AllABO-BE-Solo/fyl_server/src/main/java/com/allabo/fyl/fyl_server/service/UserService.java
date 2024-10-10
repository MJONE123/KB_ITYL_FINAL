package com.allabo.fyl.fyl_server.service;

import com.allabo.fyl.fyl_server.entity.User;
import com.allabo.fyl.fyl_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 회원가입 시 사용자 정보 저장
    public User registerUser(String name, String residentNumber, String phoneNumber, String firebaseUid) {
        User existingUser = userRepository.findByFirebaseUid(firebaseUid);

        if (existingUser == null) {
            User newUser = new User(name, residentNumber, phoneNumber, firebaseUid);
            return userRepository.save(newUser); // 새 사용자 저장
        } else {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
    }
}

