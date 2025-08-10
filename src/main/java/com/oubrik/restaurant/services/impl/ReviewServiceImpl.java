package com.oubrik.restaurant.services.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.oubrik.restaurant.domain.ReviewCreateUpdateRequest;
import com.oubrik.restaurant.domain.entities.Photo;
import com.oubrik.restaurant.domain.entities.Restaurant;
import com.oubrik.restaurant.domain.entities.Review;
import com.oubrik.restaurant.domain.entities.User;
import com.oubrik.restaurant.domain.repositories.RestaurantRepository;
import com.oubrik.restaurant.exceptions.RestaurantNotFoundException;
import com.oubrik.restaurant.exceptions.ReviewNotAllowedException;
import com.oubrik.restaurant.services.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
        private final RestaurantRepository restaurantRepository;

        @Override
        public Review createReview(User author, String restaurantId, ReviewCreateUpdateRequest review) {
                Restaurant restaurant = getRestaurantOrThrow(restaurantId);
                boolean hasExistingReview = restaurant.getReviews().stream()
                                .anyMatch(r -> r.getWrittenBy().getId().equals(author.getId()));
                if (hasExistingReview) {
                        throw new ReviewNotAllowedException("User has already reviewed this restaurant");
                }
                LocalDateTime now = LocalDateTime.now();
                List<Photo> photos = review.getPhotoIds().stream()
                                .map(url -> Photo.builder()
                                                .url(url)
                                                .uploadDate(now)
                                                .build())
                                .toList();
                String reviewId = UUID.randomUUID().toString();
                Review reviewToCreate = Review.builder()
                                .id(reviewId)
                                .content(review.getContent())
                                .rating(review.getRating())
                                .photos(photos)
                                .datePosted(now)
                                .lastEdited(now)
                                .writtenBy(author)
                                .build();
                restaurant.getReviews().add(reviewToCreate);
                updateRestaurantAverageRating(restaurant);
                Restaurant savedRestaurant = restaurantRepository.save(restaurant);
                return savedRestaurant.getReviews().stream().filter(r -> r.getId().equals(reviewId)).findFirst()
                                .orElseThrow(() -> new RuntimeException("Error retrieving created review"));
        }

        private Restaurant getRestaurantOrThrow(String restaurantId) {
                Restaurant restaurant = restaurantRepository.findById(restaurantId)
                                .orElseThrow(() -> new RestaurantNotFoundException(
                                                "Restaurant with id not found: " + restaurantId));
                return restaurant;
        }

        private void updateRestaurantAverageRating(Restaurant restaurant) {
                double averageRating = restaurant.getReviews().stream()
                                .mapToDouble(review -> review.getRating())
                                .average()
                                .orElse(0);
                restaurant.setAverageRating((float) averageRating);
        }

        @Override
        public Page<Review> listReviews(String restaurantId, Pageable pageable) {
                Restaurant restaurant = getRestaurantOrThrow(restaurantId);
                List<Review> reviews = restaurant.getReviews();
                Sort sort = pageable.getSort();
                if (sort.isSorted()) {
                        Sort.Order order = sort.stream().findFirst()
                                        .orElseThrow(() -> new RuntimeException("No sort order found"));
                        String property = order.getProperty();
                        boolean isAscending = order.getDirection().isAscending();
                        Comparator<Review> comparator = switch (property) {
                                case "rating" -> Comparator.comparing(Review::getRating);
                                default -> Comparator.comparing(Review::getDatePosted);
                        };

                        reviews.sort(isAscending ? comparator : comparator.reversed());
                } else {
                        reviews.sort(Comparator.comparing(Review::getDatePosted).reversed());
                }
                int start = (int) pageable.getOffset();
                if (start >= reviews.size()) {
                        return new PageImpl<>(Collections.emptyList(), pageable, reviews.size());
                }
                int end = Math.min((start + pageable.getPageSize()), reviews.size());
                return new PageImpl<>(reviews.subList(start, end), pageable, reviews.size());
        }
}
