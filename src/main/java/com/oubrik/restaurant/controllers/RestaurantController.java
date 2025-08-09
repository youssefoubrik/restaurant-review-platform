package com.oubrik.restaurant.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oubrik.restaurant.domain.RestaurantCreateUpdateRequest;
import com.oubrik.restaurant.domain.dtos.RestaurantCreateUpdateRequestDto;
import com.oubrik.restaurant.domain.dtos.RestaurantDto;
import com.oubrik.restaurant.domain.dtos.RestaurantSummmaryDto;
import com.oubrik.restaurant.domain.entities.Restaurant;
import com.oubrik.restaurant.mappers.RestaurantMapper;
import com.oubrik.restaurant.services.RestaurantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(
            @Valid @RequestBody RestaurantCreateUpdateRequestDto request) {
        RestaurantCreateUpdateRequest restaurantCreateUpdateRequest = restaurantMapper
                .toRestaurantCreateUpdateRequest(request);
        Restaurant restaurant = restaurantService.createRestaurant(restaurantCreateUpdateRequest);
        RestaurantDto createdRestaurantDto = restaurantMapper.toRestaurantDto(restaurant);
        return ResponseEntity.ok(createdRestaurantDto);
    }

    @GetMapping
    public Page<RestaurantSummmaryDto> searchRestaurants(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false) Float latitude,
            @RequestParam(required = false) Float longitude,
            @RequestParam(required = false) Float radius,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<Restaurant> searchResults = restaurantService.searchRestaurants(q, minRating, latitude, longitude,
                radius, PageRequest.of(page - 1, size));
        return searchResults.map(restaurantMapper::toSummmaryDto);
    }

    @GetMapping(path = "/{restaurant_id}")
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable("restaurant_id") String restaurantId) {
        return restaurantService.getRestaurant(restaurantId)
                .map(restaurantMapper::toRestaurantDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{restaurant_id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable("restaurant_id") String restaurantId,
            @Valid @RequestBody RestaurantCreateUpdateRequestDto requestDto) {
        RestaurantCreateUpdateRequest request = restaurantMapper.toRestaurantCreateUpdateRequest(requestDto);
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurantId, request);
        return ResponseEntity.ok(restaurantMapper.toRestaurantDto(updatedRestaurant));
    }
}
