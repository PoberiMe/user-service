package me.poberi.userservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("error", "Bad Request");

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                fieldErrors.put(err.getField(), err.getDefaultMessage())
        );

        body.put("errors", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            UsernameNotFoundException.class,
            BadCredentialsException.class
    })
    public ResponseEntity<Map<String, Object>> handleCommonExceptions(RuntimeException ex) {
        HttpStatus status;

        if (ex instanceof IllegalArgumentException) status = HttpStatus.BAD_REQUEST;
        else if (ex instanceof UsernameNotFoundException) status = HttpStatus.NOT_FOUND;
        else if (ex instanceof BadCredentialsException) status = HttpStatus.UNAUTHORIZED;
        else status = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status).body(buildResponse(status, ex.getMessage()));
    }

    private Map<String, Object> buildResponse(HttpStatus status, String message) {
        return Map.of(
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message
        );
    }

}

