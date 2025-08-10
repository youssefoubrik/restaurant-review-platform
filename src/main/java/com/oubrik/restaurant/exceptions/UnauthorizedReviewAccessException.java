package com.oubrik.restaurant.exceptions;

public class UnauthorizedReviewAccessException extends BaseException {
    public UnauthorizedReviewAccessException() {
        super();
    }

    public UnauthorizedReviewAccessException(String message) {
        super(message);
    }

    public UnauthorizedReviewAccessException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedReviewAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
