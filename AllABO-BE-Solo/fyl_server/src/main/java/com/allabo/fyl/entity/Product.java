package com.allabo.fyl.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;  // 신용카드, 체크카드, 예/적금, 보험, 대출
    private String description;
    private String conditions; // 추천 조건 등

    // getter, setter
}


