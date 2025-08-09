package com.oubrik.restaurant.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.oubrik.restaurant.domain.ReviewCreateUpdateRequest;
import com.oubrik.restaurant.domain.dtos.ReviewCreateUpdateRequestDto;
import com.oubrik.restaurant.domain.dtos.ReviewDto;
import com.oubrik.restaurant.domain.entities.Review;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    ReviewCreateUpdateRequest toReviewCreateUpdateRequest(ReviewCreateUpdateRequestDto dto);

    ReviewDto toDto(Review Review);
}
