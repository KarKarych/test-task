package ru.relex.market.service.logic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.relex.market.db.entity.ExchangeRate;
import ru.relex.market.service.model.exchange.Currency;
import ru.relex.market.service.model.exchange.ExchangeRateUpdateRequest;

import java.math.BigDecimal;
import java.util.EnumMap;

@Validated
public interface ExchangeRateService {

  EnumMap<Currency, BigDecimal> getExchangeRate(@NotNull Currency currency);

  ExchangeRate getExchangeRate(@NotNull Currency currencyFrom, @NotNull Currency currencyTo);

  EnumMap<Currency, BigDecimal> updateExchangeRatesByCurrency(@NotNull @Valid ExchangeRateUpdateRequest request);
}
