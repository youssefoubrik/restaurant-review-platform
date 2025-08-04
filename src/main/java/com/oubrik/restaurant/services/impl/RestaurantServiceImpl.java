package com.oubrik.restaurant.services.impl;

import static java.util.Objects.nonNull;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import com.oubrik.restaurant.domain.GeoLocation;
import com.oubrik.restaurant.domain.RestaurantCreateUpdateRequest;
import com.oubrik.restaurant.domain.entities.Address;
import com.oubrik.restaurant.domain.entities.Photo;
import com.oubrik.restaurant.domain.entities.Restaurant;
import com.oubrik.restaurant.domain.repositories.RestaurantRepository;
import com.oubrik.restaurant.services.GeoLocationService;
import com.oubrik.restaurant.services.RestaurantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final GeoLocationService geoLocationService;

    @Override
    public Restaurant createRestaurant(RestaurantCreateUpdateRequest request) {
        Address address = request.getAddress();
        GeoLocation geoLocation = geoLocationService.geoLocate(address);
        GeoPoint geoPoint = new GeoPoint(geoLocation.getLatitude(), geoLocation.getLongitude());
        List<String> photoIds = request.getPhotoIds();
        List<Photo> photos = photoIds.stream()
                .map(photoUrl -> Photo.builder().url(photoUrl).uploadDate(LocalDateTime.now()).build())
                .toList();
        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .cuisineType(request.getCuisineType())
                .contactInformation(request.getContactInformation())
                .address(request.getAddress())
                .geoLocation(geoPoint)
                .operatingHours(request.getOperatingHours())
                .averageRating(0f)
                .photos(photos)
                .build();
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Page<Restaurant> searchRestaurants(String query, Float minRating, Float latitude, Float longitude,
            Float radius, Pageable pageable) {

        if (nonNull(latitude) && nonNull(longitude) && nonNull(radius)) {
            return restaurantRepository.findByLocationNear(latitude, longitude, radius, pageable);
        }

        Float searchMinRating = (minRating != null) ? minRating : 0f;
        if (nonNull(query) && !query.trim().isEmpty()) {
            return restaurantRepository.findByQueryAndMinRating(query.trim(), searchMinRating, pageable);
        }

        if (nonNull(minRating)) {
            return restaurantRepository.findByAverageRatingGreaterThanEqual(minRating, pageable);
        }

        return restaurantRepository.findAll(pageable);
    }

}
