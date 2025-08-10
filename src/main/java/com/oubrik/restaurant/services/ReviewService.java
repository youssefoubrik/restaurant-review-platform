package com.oubrik.restaurant.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oubrik.restaurant.domain.ReviewCreateUpdateRequest;
import com.oubrik.restaurant.domain.entities.Review;
import com.oubrik.restaurant.domain.entities.User;

public interface ReviewService {
    Review createReview(User author, String restaurantId, ReviewCreateUpdateRequest review);

    Page<Review> listReviews(String restaurantId, Pageable pageable);
}
