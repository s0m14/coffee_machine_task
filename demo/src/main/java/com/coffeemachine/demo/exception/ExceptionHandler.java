package com.coffeemachine.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> exceptionHandler(RequestException e){
            ApiException apiException = new ApiException(
                    e.getMessage(),
                    e,
                    HttpStatus.BAD_REQUEST,
                    LocalDateTime.now()
            );

            return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
}
