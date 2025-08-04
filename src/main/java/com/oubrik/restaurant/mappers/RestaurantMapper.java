package com.oubrik.restaurant.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import com.oubrik.restaurant.domain.RestaurantCreateUpdateRequest;
import com.oubrik.restaurant.domain.dtos.GeoPointDto;
import com.oubrik.restaurant.domain.dtos.RestaurantCreateUpdateRequestDto;
import com.oubrik.restaurant.domain.dtos.RestaurantDto;
import com.oubrik.restaurant.domain.dtos.RestaurantSummmaryDto;
import com.oubrik.restaurant.domain.entities.Restaurant;
import com.oubrik.restaurant.domain.entities.Review;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RestaurantMapper {
    RestaurantCreateUpdateRequest toRestaurantCreateUpdateRequest(RestaurantCreateUpdateRequestDto dto);

    @Mapping(target = "totalReviews", source = "reviews", qualifiedByName = "populateTotalReviews")
    RestaurantSummmaryDto toSummmaryDto(Restaurant restaurant);

    RestaurantDto toRestaurantDto(Restaurant restaurant);

    @Mapping(target = "latitude", expression = "java(geoPoint.getLat())")
    @Mapping(target = "longitude", expression = "java(geoPoint.getLon())")
    GeoPointDto toGeoPointDto(GeoPoint geoPoint);

    @Named("populateTotalReviews")
    default Integer populateTotalReviews(List<Review> reviews) {
        return reviews.size();
    }
}