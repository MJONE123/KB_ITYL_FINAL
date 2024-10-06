package com.allabo.fyl.controller;

import com.allabo.fyl.dto.FavoriteDto;
import com.allabo.fyl.entity.Favorite;
import com.allabo.fyl.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    // 즐겨찾기 추가
    @PostMapping("/add")
    public void addFavorite(@RequestParam Long userId, @RequestBody FavoriteDto favoriteDto) {
        favoriteService.addFavorite(userId, favoriteDto.getProductId());
    }

    // 사용자별 즐겨찾기 목록 조회
    @GetMapping("/list")
    public List<Favorite> getFavorites(@RequestParam Long userId) {
        return favoriteService.getFavoritesByUser(userId);
    }
}
