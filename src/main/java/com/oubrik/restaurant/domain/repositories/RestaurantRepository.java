package com.oubrik.restaurant.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.oubrik.restaurant.domain.entities.Restaurant;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, String> {

        Page<Restaurant> findByAverageRatingGreaterThanEqual(Float minRating, Pageable pageable);

        // Simple text search using Spring Data Elasticsearch query methods
        Page<Restaurant> findByNameContainingIgnoreCaseOrCuisineTypeContainingIgnoreCase(
                        String name, String cuisineType, Pageable pageable);

        @Query("{" +
                        "  \"bool\": {" +
                        "    \"must\": [" +
                        "      {\"range\": {\"averageRating\": {\"gte\": ?1}}}," +
                        "      {\"bool\": {" +
                        "        \"should\": [" +
                        "          {\"match\": {\"name\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}," +
                        "          {\"match\": {\"cuisineType\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}" +
                        "        ]," +
                        "        \"minimum_should_match\": 1" +
                        "      }}" +
                        "    ]" +
                        "  }" +
                        "}")
        Page<Restaurant> findByQueryAndMinRating(String query, Float minRating, Pageable pageable);

        @Query("{" +
                        "  \"bool\": {" +
                        "    \"must\": [" +
                        "      {\"geo_distance\": {" +
                        "        \"distance\": \"?2km\"," +
                        "        \"geoLocation\": {" +
                        "          \"lat\": ?0," +
                        "          \"lon\": ?1" +
                        "        }" +
                        "      }}" +
                        "    ]" +
                        "  }" +
                        "}")
        Page<Restaurant> findByLocationNear(
                        Float latitude,
                        Float longitude,
                        Float radiusKm,
                        Pageable pageable);

}