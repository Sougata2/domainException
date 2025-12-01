package com.domain.exception.advice;

import com.domain.exception.dto.ErrorDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDto> exceptionHandler(Exception exception, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());
        errorDto.setPath("[%s] : %s".formatted(request.getMethod(), request.getRequestURI()));
        errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorDto> exceptionHandler(EntityNotFoundException exception, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());
        errorDto.setPath("[%s] : %s".formatted(request.getMethod(), request.getRequestURI()));
        errorDto.setStatus(HttpStatus.NOT_FOUND);
        errorDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }
}
