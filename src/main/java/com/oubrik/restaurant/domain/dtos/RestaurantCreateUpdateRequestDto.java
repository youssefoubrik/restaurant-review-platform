package com.oubrik.restaurant.domain.dtos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class RestaurantCreateUpdateRequestDto {
    @NotBlank(message = "Restaurant name is required")
    private String name;
    @NotBlank(message = "Cuisine type is required")
    private String cuisineType;
    @NotBlank(message = "Contact information is required")
    private String contactInformation;
    @Valid
    private AddressDto address;
    @Valid
    private OperatingHoursDto operatingHours;
    @Size(min = 1, message = "At least one photo ID is required")
    private List<String> photoIds;
}
