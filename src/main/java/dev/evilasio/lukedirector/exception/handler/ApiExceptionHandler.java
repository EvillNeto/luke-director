package dev.evilasio.lukedirector.exception.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.evilasio.lukedirector.exception.StandardException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(StandardException.class)
    public ResponseEntity<ErrorDto> handle(StandardException e, HttpServletRequest request) {
        HttpStatus status = e.getStatus();
        ErrorDto error = new ErrorDto(
                Instant.now(),
                status.value(),
                e.getName(),
                e.getMessage(),
                request.getRequestURI(),
                e.getExtraInfo());
        return ResponseEntity.status(status).body(error);
    }
}
