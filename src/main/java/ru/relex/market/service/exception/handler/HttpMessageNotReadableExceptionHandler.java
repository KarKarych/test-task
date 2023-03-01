package ru.relex.market.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.relex.market.service.exception.model.Error;

import java.util.List;

@ControllerAdvice
public class HttpMessageNotReadableExceptionHandler {

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Error> handleHttpMessageNotReadableException() {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new Error(List.of("Validation error")));
  }
}
