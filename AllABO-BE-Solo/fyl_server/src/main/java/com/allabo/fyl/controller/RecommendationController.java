package com.allabo.fyl.controller;

import com.allabo.fyl.entity.Recommendation;
import com.allabo.fyl.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    // 맞춤 추천 상품 조회 API
    @GetMapping("/recommend")
    public List<Recommendation> recommendProducts(@RequestParam int age, @RequestParam int income, @RequestParam String category) {
        return recommendationService.getRecommendationsByPreferences(age, income, category);
    }
}
