package ru.relex.market.service.exception.badrequest;

import ru.relex.market.service.model.exchange.Currency;

import java.math.BigDecimal;

public class InsufficientFundsException extends BadRequestException {

  public InsufficientFundsException(BigDecimal amount, Currency currency) {
    super("Insufficient funds on the balance. You only have %s %s".formatted(amount.toString(), currency));
  }
}
