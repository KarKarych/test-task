package ru.relex.market.service.exception.internalerror;

public class InternalServerErrorException extends RuntimeException {

  public InternalServerErrorException(String message) {
    super(message);
  }
}
