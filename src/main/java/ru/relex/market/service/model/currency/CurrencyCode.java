package ru.relex.market.service.model.currency;

import java.util.Optional;

public enum CurrencyCode {
  RUB,
  BTC,
  TON;

  public static Optional<CurrencyCode> fromString(String stringCode) {
    if (stringCode == null) {
      return Optional.empty();
    }

    for (CurrencyCode value : values()) {
      if (stringCode.equalsIgnoreCase(value.toString())) {
        return Optional.of(value);
      }
    }

    return Optional.empty();
  }
}
