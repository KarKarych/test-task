package ru.relex.market.service.exception.currency;

import ru.relex.market.service.exception.BadRequestException;

public class MissingCurrencyException extends BadRequestException {

  public MissingCurrencyException(String message) {
    super(message);
  }
}
