package ru.relex.market.service.mapper;

import org.springframework.stereotype.Component;
import ru.relex.market.db.entity.ExchangeRate;
import ru.relex.market.db.entity.ExchangeRateId;
import ru.relex.market.service.model.exchange.Currency;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExchangeRateMapper {

  public EnumMap<Currency, BigDecimal> toCurrencyRatesMap(List<ExchangeRate> exchangeRates) {
    if (exchangeRates == null) {
      return null;
    }

    return exchangeRates.stream()
      .collect(Collectors.toMap(
        ExchangeRate::getCurrencyTo,
        ExchangeRate::getRate,
        (rate1, rate2) -> rate2,
        () -> new EnumMap<>(Currency.class)
      ));
  }

  public ExchangeRate toEntity(Currency baseCurrency, Currency secondCurrency, BigDecimal secondRate) {
    return ExchangeRate.builder()
      .id(new ExchangeRateId(baseCurrency, secondCurrency))
      .rate(secondRate)
      .build();
  }
}
