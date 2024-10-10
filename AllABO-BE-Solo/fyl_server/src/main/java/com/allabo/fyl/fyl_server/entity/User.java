package com.allabo.fyl.fyl_server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;                  // 사용자 이름
    private String residentNumber;        // 주민번호 앞자리
    private String phoneNumber;           // 전화번호
    private String firebaseUid;           // Firebase로부터 받은 고유 UID

    // 생성자, getter, setter
    public User() {}

    public User(String name, String residentNumber, String phoneNumber, String firebaseUid) {
        this.name = name;
        this.residentNumber = residentNumber;
        this.phoneNumber = phoneNumber;
        this.firebaseUid = firebaseUid;
    }

    // Getter and Setter
    // ...
}