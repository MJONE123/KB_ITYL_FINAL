package com.allabo.fyl.service;

import com.allabo.fyl.entity.Favorite;
import com.allabo.fyl.entity.Product;
import com.allabo.fyl.entity.User;
import com.allabo.fyl.repository.FavoriteRepository;
import com.allabo.fyl.repository.ProductRepository;
import com.allabo.fyl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // 즐겨찾기 추가
    public void addFavorite(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("상품 정보를 찾을 수 없습니다."));

        Favorite favorite = new Favorite(user, product);
        favoriteRepository.save(favorite);
    }

    // 사용자별 즐겨찾기 리스트 조회
    public List<Favorite> getFavoritesByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));
        return favoriteRepository.findByUser(user);
    }
}
