package com.allabo.fyl.service;

import com.allabo.fyl.entity.Recommendation;
import com.allabo.fyl.repository.RecommendationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationMapper recommendationMapper;

    // 사용자 맞춤 추천 상품 목록 조회
    public List<Recommendation> getRecommendationsByPreferences(int age, int income, String category) {
        return recommendationMapper.selectRecommendationsByPreferences(age, income, category);
    }
}
