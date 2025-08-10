package com.oubrik.restaurant.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oubrik.restaurant.domain.ReviewCreateUpdateRequest;
import com.oubrik.restaurant.domain.dtos.ReviewCreateUpdateRequestDto;
import com.oubrik.restaurant.domain.dtos.ReviewDto;
import com.oubrik.restaurant.domain.entities.Review;
import com.oubrik.restaurant.domain.entities.User;
import com.oubrik.restaurant.mappers.ReviewMapper;
import com.oubrik.restaurant.services.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(
            @PathVariable String restaurantId,
            @Valid @RequestBody ReviewCreateUpdateRequestDto review,
            @AuthenticationPrincipal Jwt jwt) {
        ReviewCreateUpdateRequest reviewCreateUpdateRequest = reviewMapper.toReviewCreateUpdateRequest(review);
        Review createdReview = reviewService.createReview(jwtToUser(jwt), restaurantId, reviewCreateUpdateRequest);
        return ResponseEntity.ok(reviewMapper.toDto(createdReview));
    }

    @GetMapping(path = "/{reviewId}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable String restaurantId, @PathVariable String reviewId) {
        return reviewService.getReview(restaurantId, reviewId).map(reviewMapper::toDto).map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.noContent().build());
    }

    @GetMapping
    public Page<ReviewDto> listReviews(@PathVariable String restaurantId,
            @PageableDefault(size = 20, page = 0, sort = "datePosted", direction = Direction.DESC) Pageable pageable) {
        return reviewService.listReviews(restaurantId, pageable).map(reviewMapper::toDto);
    }

    private User jwtToUser(Jwt jwt) {
        return User.builder()
                .id(jwt.getSubject())
                .username(jwt.getClaimAsString("preferred_username"))
                .givenName(jwt.getClaimAsString("given_name"))
                .familyName(jwt.getClaimAsString("family_name"))
                .build();
    }
}
