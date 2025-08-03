package com.oubrik.restaurant.domain.entities;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeRange {
    @Field(type = FieldType.Keyword)
    private String openTime;

    @Field(type = FieldType.Keyword)
    private String closeTime;
}
