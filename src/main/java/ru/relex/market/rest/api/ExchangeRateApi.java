package ru.relex.market.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.relex.market.service.model.exchange.Currency;

import java.math.BigDecimal;
import java.util.EnumMap;

public interface ExchangeRateApi {

  @Operation(security = {@SecurityRequirement(name = "secret_key")})
  @GetMapping("/{currency}/exchangeRates")
  EnumMap<Currency, BigDecimal> getExchangeRatesByCurrency(@PathVariable Currency currency);

  @Operation(security = {@SecurityRequirement(name = "secret_key")})
  @PostMapping("/{currency}/exchangeRates")
  EnumMap<Currency, BigDecimal> updateExchangeRatesByCurrency(
    @PathVariable Currency currency,
    @RequestBody EnumMap<Currency, BigDecimal> currencyToRate
  );
}
