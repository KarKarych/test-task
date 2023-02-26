package ru.relex.market.service.exception.currency;


import ru.relex.market.service.exception.BadRequestException;

public class UnsupportedCurrencyRateException extends BadRequestException {

  public UnsupportedCurrencyRateException(String message) {
    super(message);
  }
}
