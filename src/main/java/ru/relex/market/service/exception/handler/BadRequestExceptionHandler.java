package ru.relex.market.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.relex.market.service.exception.BadRequestException;
import ru.relex.market.service.exception.model.Error;

@ControllerAdvice
public class BadRequestExceptionHandler extends RuntimeException {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Error> handleBaseException(BadRequestException badRequestException) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new Error(badRequestException.getMessage()));
  }
}
