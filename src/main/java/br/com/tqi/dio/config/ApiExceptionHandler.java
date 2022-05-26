package br.com.tqi.dio.config;

import br.com.tqi.dio.exception.PostalCodeNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handlerInternalServerError(Exception e, Locale locale) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Collections.singletonMap("message", "Unexpected error on app = " + e.getMessage()));
    }

    @ExceptionHandler(PostalCodeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlerNotFound(Exception e, Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Collections.singletonMap("message", e.getMessage()));
    }

}
