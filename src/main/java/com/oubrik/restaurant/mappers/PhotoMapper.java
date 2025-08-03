package com.oubrik.restaurant.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.oubrik.restaurant.domain.dtos.PhotoDto;
import com.oubrik.restaurant.domain.entities.Photo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {
    PhotoDto toDto(Photo photo);
}