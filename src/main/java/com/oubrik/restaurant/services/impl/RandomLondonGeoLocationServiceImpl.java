package com.oubrik.restaurant.services.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.oubrik.restaurant.domain.GeoLocation;
import com.oubrik.restaurant.domain.entities.Address;
import com.oubrik.restaurant.services.GeoLocationService;

@Service
public class RandomLondonGeoLocationServiceImpl implements GeoLocationService {
    private static final float MIN_LATITUDE = 52.28f;
    private static final float MAX_LATITUDE = 51.686f;

    private static final float MIN_LONGITUDE = -0.489f;
    private static final float MAX_LONGITUDE = 0.236f;

    @Override
    public GeoLocation geoLocate(Address Address) {
        Random random = new Random();
        double latitude = MIN_LATITUDE + random.nextDouble() * (MAX_LATITUDE - MIN_LATITUDE);
        double longitude = MIN_LONGITUDE + random.nextDouble() * (MAX_LONGITUDE - MIN_LONGITUDE);
        return GeoLocation.builder().longitude(longitude).latitude(latitude).build();
    }

}
