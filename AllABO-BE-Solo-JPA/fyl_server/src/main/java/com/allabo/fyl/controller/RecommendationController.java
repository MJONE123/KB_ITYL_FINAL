package com.allabo.fyl.controller;


import com.allabo.fyl.dto.RecommendationDto;
import com.allabo.fyl.entity.Product;
import com.allabo.fyl.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/submit")
    public List<Product> recommendProducts(@RequestBody RecommendationDto dto) {
        return recommendationService.getRecommendedProducts(dto);
    }
}
