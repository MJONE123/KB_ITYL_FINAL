package com.allabo.fyl.controller;

import com.allabo.fyl.dto.FavoriteDto;
import com.allabo.fyl.entity.Favorite;
import com.allabo.fyl.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    // 즐겨찾기 추가 (로그인된 사용자만 사용 가능)
    @PostMapping("/add")
    public void addFavorite(@AuthenticationPrincipal UserDetails userDetails, @RequestBody FavoriteDto favoriteDto) {
        // 로그인된 사용자 정보를 이용해 즐겨찾기 추가
        String username = userDetails.getUsername();
        favoriteService.addFavorite(username, favoriteDto.getProductId());
    }

    // 사용자별 즐겨찾기 목록 조회 (로그인된 사용자만 사용 가능)
    @GetMapping("/list")
    public List<Favorite> getFavorites(@AuthenticationPrincipal UserDetails userDetails) {
        // 로그인된 사용자 정보를 이용해 즐겨찾기 목록 조회
        String username = userDetails.getUsername();
        return favoriteService.getFavoritesByUsername(username);
    }
}