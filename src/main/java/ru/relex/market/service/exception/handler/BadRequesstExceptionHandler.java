package ru.relex.market.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.relex.market.service.exception.badrequest.BadRequestException;
import ru.relex.market.service.exception.model.Error;

import java.util.List;

@ControllerAdvice
public class BadRequesstExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Error> handleBaseException(BadRequestException exception) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new Error(List.of(exception.getMessage())));
  }
}
