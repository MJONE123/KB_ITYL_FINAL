package com.allabo.fyl.repository;

import com.allabo.fyl.entity.Recommendation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecommendationMapper {

    // 사용자 맞춤 추천 상품 목록 조회
    List<Recommendation> selectRecommendationsByPreferences(@Param("age") int age, @Param("income") int income, @Param("category") String category);
}
