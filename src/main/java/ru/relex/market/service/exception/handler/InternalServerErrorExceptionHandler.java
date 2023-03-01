package ru.relex.market.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.relex.market.service.exception.internalerror.InternalServerErrorException;
import ru.relex.market.service.exception.model.Error;

import java.util.List;

@ControllerAdvice
public class InternalServerErrorExceptionHandler {

  @ExceptionHandler(InternalServerErrorException.class)
  public ResponseEntity<Error> handleInternalServerErrorException(InternalServerErrorException exception) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(new Error(List.of(exception.getMessage())));
  }
}
