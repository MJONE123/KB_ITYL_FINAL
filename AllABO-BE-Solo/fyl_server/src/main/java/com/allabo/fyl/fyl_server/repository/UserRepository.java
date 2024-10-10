package com.allabo.fyl.fyl_server.repository;

import com.allabo.fyl.fyl_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Firebase UID로 사용자 검색
    User findByFirebaseUid(String firebaseUid);
}