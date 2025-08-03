package com.oubrik.restaurant.domain.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.oubrik.restaurant.domain.entities.Restaurant;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, String> {
    // TODO: Custom queries;
}
