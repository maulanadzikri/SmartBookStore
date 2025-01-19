package com.smartbookstore.client.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    public String handle500Error(Exception e) {
        // return "/auth/error";
        return "index";
    }
}

