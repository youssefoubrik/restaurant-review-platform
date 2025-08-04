package com.oubrik.restaurant.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import com.oubrik.restaurant.domain.RestaurantCreateUpdateRequest;
import com.oubrik.restaurant.domain.dtos.GeoPointDto;
import com.oubrik.restaurant.domain.dtos.RestaurantCreateUpdateRequestDto;
import com.oubrik.restaurant.domain.dtos.RestaurantDto;
import com.oubrik.restaurant.domain.entities.Restaurant;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RestaurantMapper {
    RestaurantCreateUpdateRequest toRestaurantCreateUpdateRequest(RestaurantCreateUpdateRequestDto dto);

    RestaurantDto toRestaurantDto(Restaurant restaurant);

    @Mapping(target = "latitude", expression = "java(geoPoint.getLat())")
    @Mapping(target = "longitude", expression = "java(geoPoint.getLon())")
    GeoPointDto toGeoPointDto(GeoPoint geoPoint);
}