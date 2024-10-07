package com.allabo.fyl.repository;

import com.allabo.fyl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Firebase UID로 사용자 검색
    User findByFirebaseUid(String firebaseUid);
}