package com.oubrik.restaurant.domain.dtos;

import jakarta.validation.Valid;
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
public class OperatingHoursDto {
    @Valid
    private TimeRangeDto monday;
    @Valid
    private TimeRangeDto tuesday;
    @Valid
    private TimeRangeDto wednesday;
    @Valid
    private TimeRangeDto thursday;
    @Valid
    private TimeRangeDto friday;
    @Valid
    private TimeRangeDto saturday;
    @Valid
    private TimeRangeDto sunday;
}
