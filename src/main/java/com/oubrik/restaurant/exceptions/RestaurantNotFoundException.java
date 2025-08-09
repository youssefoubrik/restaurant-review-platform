package com.oubrik.restaurant.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Throwable cause) {
        super(cause);
    }

    public RestaurantNotFoundException() {
    }

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
