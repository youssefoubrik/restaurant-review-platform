package com.oubrik.restaurant.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.oubrik.restaurant.domain.dtos.ErrorDto;
import com.oubrik.restaurant.exceptions.BaseException;
import com.oubrik.restaurant.exceptions.StorageException;

import lombok.extern.slf4j.Slf4j;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {
    @ExceptionHandler(StorageException.class)
    public ResponseEntity<ErrorDto> handleStorageException(StorageException ex) {
        log.error("Caught Storage Exception", ex);
        ErrorDto errorDto = ErrorDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Unable to save or retrieve resources at this time").build();
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDto> handleBaseException(BaseException ex) {
        log.error("Caught BaseException", ex);
        ErrorDto errorDto = ErrorDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occured").build();
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(BaseException ex) {
        log.error("Caught unexpected exception", ex);
        ErrorDto errorDto = ErrorDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occured").build();
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
