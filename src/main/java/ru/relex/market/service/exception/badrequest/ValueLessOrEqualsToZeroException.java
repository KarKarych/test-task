package ru.relex.market.service.exception.badrequest;

public class ValueLessOrEqualsToZeroException extends RuntimeException {

  public ValueLessOrEqualsToZeroException(String message) {
    super(message);
  }
}
