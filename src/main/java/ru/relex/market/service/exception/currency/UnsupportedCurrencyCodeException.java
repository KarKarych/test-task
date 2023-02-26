package ru.relex.market.service.exception.currency;

import ru.relex.market.service.exception.BadRequestException;

public class UnsupportedCurrencyCodeException extends BadRequestException {

  public UnsupportedCurrencyCodeException(String message) {
    super(message);
  }
}
