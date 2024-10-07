package com.allabo.fyl.repository;

import com.allabo.fyl.entity.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FavoriteMapper {

    // 즐겨찾기 추가
    void insertFavorite(@Param("userId") Long userId, @Param("productId") Long productId, @Param("productType") String productType);

    // 사용자별 즐겨찾기 목록 조회
    List<Favorite> selectFavoritesByUserId(Long userId);

    // 즐겨찾기 삭제 (마이페이지에서 삭제 가능)
    void deleteFavorite(@Param("userId") Long userId, @Param("productId") Long productId, @Param("productType") String productType);
}
