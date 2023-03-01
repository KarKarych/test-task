package ru.relex.market.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.relex.market.service.exception.auth.RequestForbiddenException;
import ru.relex.market.service.exception.model.Error;

import java.util.List;

@ControllerAdvice
public class ForbiddenExceptionHandler {

  @ExceptionHandler(RequestForbiddenException.class)
  public ResponseEntity<Error> handleForbiddenException(RequestForbiddenException exception) {
    return ResponseEntity
      .status(HttpStatus.FORBIDDEN)
      .body(new Error(List.of(exception.getMessage())));
  }
}
