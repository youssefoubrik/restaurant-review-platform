package com.oubrik.restaurant.domain.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private String id;
    private String content;
    private Integer rating;
    private LocalDateTime datePosted;
    private LocalDateTime lastEdited;
    @Builder.Default
    private List<PhotoDto> photos = new ArrayList<>();
    private UserDto writtenBy;
}
