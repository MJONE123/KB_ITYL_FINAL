package com.allabo.fyl.controller;

import com.allabo.fyl.dto.FavoriteDto;
import com.allabo.fyl.entity.Favorite;
import com.allabo.fyl.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    // 즐겨찾기 추가
//    @PostMapping("/add")
//    public void addFavorite(@AuthenticationPrincipal UserDetails userDetails, @RequestBody FavoriteDto favoriteDto) {
//        Long userId = Long.parseLong(userDetails.getUsername()); // 사용자 ID 가져오기 (예시)
//        favoriteService.addFavorite(userId, favoriteDto.getProductId(), favoriteDto.getProductType());
//    }
    @PostMapping("/add")
    public void addFavorite(@AuthenticationPrincipal UserDetails userDetails, @RequestBody FavoriteDto favoriteDto) {
        System.out.println(favoriteDto);
        Long userId = Long.parseLong(userDetails.getUsername()); // 사용자 ID 가져오기 (예시)
        favoriteService.addFavorite(userId, favoriteDto.getProductId(), favoriteDto.getProductType());
//        favoriteService.addFavorite(, favoriteDto.getProductId(), favoriteDto.getProductType());

//       @PostMapping("/add")
//    public void addFavorite(@RequestBody FavoriteDto favoriteDto) {
//        favoriteService.addFavorite(favoriteDto.getUserId(), favoriteDto.getProductId(), favoriteDto.getProductType());
//    }
    }

    // 즐겨찾기 목록 조회
    @GetMapping("/list")
    public List<Favorite> getFavorites(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername()); // 사용자 ID 가져오기
        return favoriteService.getFavoritesByUserId(userId);
    }

    // 즐겨찾기 삭제
    @DeleteMapping("/remove")
    public void removeFavorite(@AuthenticationPrincipal UserDetails userDetails, @RequestBody FavoriteDto favoriteDto) {
        Long userId = Long.parseLong(userDetails.getUsername()); // 사용자 ID 가져오기
        favoriteService.removeFavorite(userId, favoriteDto.getProductId(), favoriteDto.getProductType());
    }
}
