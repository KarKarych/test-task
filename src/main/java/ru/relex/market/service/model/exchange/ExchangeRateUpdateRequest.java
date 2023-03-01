package ru.relex.market.service.model.exchange;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.EnumMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateUpdateRequest {

  @NotNull
  private Currency baseCurrency;

  @NotNull
  private EnumMap<Currency, BigDecimal> currencyToRate;
}
