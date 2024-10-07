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

    // 설문 결과에 따른 맞춤 상품 추천
    public List<Recommendation> getRecommendationsBySurvey(String benefits, Double annualFeeLimit, String issuer) {
        return recommendationMapper.selectRecommendationsBySurvey(benefits, annualFeeLimit, issuer);
    }
}
