package com.allabo.fyl.service;

import com.allabo.fyl.dto.RecommendationDto;
import com.allabo.fyl.entity.Product;
import com.allabo.fyl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getRecommendedProducts(RecommendationDto dto) {
        // 설문에 따라 필터링 로직 적용
        // 예: dto의 선택 항목을 기준으로 카테고리 필터링
        List<Product> recommendedProducts = new ArrayList<>();
        if (dto.getPreferredCategory().equals("신용카드")) {
            recommendedProducts = productRepository.findByCategory("신용카드");
        } else if (dto.getPreferredCategory().equals("예적금")) {
            recommendedProducts = productRepository.findByCategory("예적금");
        }
        // 추가적인 필터링 로직
        return recommendedProducts;
    }
}