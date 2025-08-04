package com.oubrik.restaurant.services;

import com.oubrik.restaurant.domain.GeoLocation;
import com.oubrik.restaurant.domain.entities.Address;

public interface GeoLocationService {
    GeoLocation geoLocate(Address Address);
}
