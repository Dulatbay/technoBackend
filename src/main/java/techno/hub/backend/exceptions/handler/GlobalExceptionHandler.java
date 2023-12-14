package techno.hub.backend.exceptions.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import techno.hub.backend.dtos.ErrorResponseDto;
import techno.hub.backend.exceptions.DbObjectNotFoundException;

import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(DbObjectNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlePositionNotFoundException(DbObjectNotFoundException ex) {
        log.error("DbObjectNotFoundException exception: ", ex);
        ErrorResponseDto errorResponse = new ErrorResponseDto(ex.getMessage(), ex.getError(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> argumentExceptionHandler(IllegalArgumentException e) {
        log.error("Argument exception: ", e);
        var errorResponse = new ErrorResponseDto(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
