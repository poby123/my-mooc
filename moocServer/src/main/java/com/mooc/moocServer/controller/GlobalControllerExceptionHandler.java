package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    private HttpHeaders resHeaders = new HttpHeaders();

    GlobalControllerExceptionHandler(){
        resHeaders.add("Content-Type" , "application/json;charset=UTF-8");
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleNotFound(HttpServletRequest req, Exception ex){
        return new ResponseEntity<>(new ErrorDto(req.getRequestURL().toString(), ex), resHeaders, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody()
    public ResponseEntity<ErrorDto> handleBadRequest(HttpServletRequest req, Exception ex){
        return new ResponseEntity<>(new ErrorDto(req.getRequestURL().toString(), ex), resHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalAccessException.class)
    @ResponseBody()
    public ResponseEntity<ErrorDto> handleBadAccess(HttpServletRequest req, Exception ex){
        return new ResponseEntity<>(new ErrorDto(req.getRequestURL().toString(), ex), resHeaders, HttpStatus.BAD_REQUEST);
    }
}
