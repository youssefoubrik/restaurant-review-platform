package com.oubrik.restaurant.domain.dtos;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ReviewCreateUpdateRequestDto {
    @NotBlank(message = "Review content is required")
    private String content;
    @NotNull(message = "Rating is required")
    @Min(message = "Rating must be between 1 and 5  ", value = 1)
    @Max(message = "Rating must be between 1 and 5  ", value = 5)
    private Integer rating;
    @Builder.Default
    private List<String> photoIds = new ArrayList<>();
}
