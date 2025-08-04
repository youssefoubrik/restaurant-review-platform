package com.oubrik.restaurant.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oubrik.restaurant.domain.RestaurantCreateUpdateRequest;
import com.oubrik.restaurant.domain.entities.Restaurant;

public interface RestaurantService {
    Restaurant createRestaurant(RestaurantCreateUpdateRequest request);

    Page<Restaurant> searchRestaurants(
            String query,
            Float minRating,
            Float latitude,
            Float longitude,
            Float radius,
            Pageable pageable);
}
