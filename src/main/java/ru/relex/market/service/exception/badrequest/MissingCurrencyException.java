package ru.relex.market.service.exception.badrequest;

public class MissingCurrencyException extends BadRequestException {

  public MissingCurrencyException(String missingCurrencies) {
    super("Missing following currencies: %s".formatted(missingCurrencies));
  }
}
