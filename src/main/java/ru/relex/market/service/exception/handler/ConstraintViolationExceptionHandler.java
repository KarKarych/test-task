package ru.relex.market.service.exception.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.relex.market.service.exception.model.Error;

import java.util.List;
import java.util.regex.Pattern;

@ControllerAdvice
public class ConstraintViolationExceptionHandler {

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Error> handleConstraintViolationException(ConstraintViolationException e) {
    List<String> errors = e.getConstraintViolations().stream()
      .map(this::createErrorMessage)
      .filter(StringUtils::isNotBlank)
      .toList();

    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new Error(errors));
  }

  private <T> String createErrorMessage(ConstraintViolation<T> cv) {
    String propertyName = getPropertyName(cv.getPropertyPath().toString());
    return propertyName + " " + cv.getMessage();
  }

  private String getPropertyName(String propertyPath) {
    String[] split = StringUtils.split(propertyPath, ".");
    if (split.length == 0) {
      return "";
    }

    String propertyName = split[split.length - 1];
    return Pattern.compile("([a-z])([A-Z]+)")
      .matcher(propertyName)
      .replaceAll("$1_$2")
      .toLowerCase();
  }
}
