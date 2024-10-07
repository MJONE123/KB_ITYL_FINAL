package com.allabo.fyl.entity;

import javax.persistence.*;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // 즐겨찾기한 사용자 정보

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;  // 즐겨찾기한 상품 정보

    // 기본 생성자
    public Favorite() {}

    // 생성자
    public Favorite(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    // Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
