package com.example.technoBackend.exceptions.handler;

import com.example.technoBackend.dtos.ErrorResponseDto;
import com.example.technoBackend.exceptions.DbObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(DbObjectNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlePositionNotFoundException(DbObjectNotFoundException ex) {
        log.error("DbObjectNotFoundException exception: ", ex);
        ErrorResponseDto errorResponse = new ErrorResponseDto(ex.getMessage(), ex.getError(), getStackTrace(ex).trim());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> argumentExceptionHandler(IllegalArgumentException e) {
        log.error("Argument exception: ", e);
        var errorResponse = new ErrorResponseDto(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), getStackTrace(e).trim());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    private static String getStackTrace(Throwable e) {
        filterStackTracesByProjectPackage(e);
        filterStackTracesByProjectPackage(e.getCause());
        return ExceptionUtils.getStackTrace(e);
    }

    private static void filterStackTracesByProjectPackage(Throwable ex) {
        if (ex == null) return;

        StackTraceElement[] stackTraces = Arrays.stream(ex.getStackTrace())
                .filter(se -> se.getClassName().startsWith("com."))
                .toArray(StackTraceElement[]::new);

        ex.setStackTrace(stackTraces);
    }
}
