package ru.relex.market.service.model.exchange;

import java.util.Optional;

public enum Currency {

  RUB,
  BTC,
  TON;

  public static Optional<Currency> fromStringToCurrency(String stringCode) {
    if (stringCode == null) {
      return Optional.empty();
    }

    for (Currency value : values()) {
      if (stringCode.equalsIgnoreCase(value.toString())) {
        return Optional.of(value);
      }
    }

    return Optional.empty();
  }
}
