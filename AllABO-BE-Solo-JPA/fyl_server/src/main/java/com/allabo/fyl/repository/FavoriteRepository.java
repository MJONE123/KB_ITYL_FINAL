package com.allabo.fyl.repository;

import com.allabo.fyl.entity.Favorite;
import com.allabo.fyl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);  // 사용자별 즐겨찾기 리스트 조회
}
