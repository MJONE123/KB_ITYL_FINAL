package com.allabo.fyl.repository;

import com.allabo.fyl.entity.Recommendation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecommendationMapper {

    // 설문 결과에 따른 맞춤 상품 필터링
    List<Recommendation> selectRecommendationsBySurvey(
            @Param("benefits") String benefits,
            @Param("annualFeeLimit") Double annualFeeLimit,
            @Param("issuer") String issuer
    );
}
