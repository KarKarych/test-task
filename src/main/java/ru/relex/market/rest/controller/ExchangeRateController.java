package ru.relex.market.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.relex.market.rest.api.ExchangeRateApi;
import ru.relex.market.service.logic.ExchangeRateService;
import ru.relex.market.service.model.exchange.Currency;
import ru.relex.market.service.model.exchange.ExchangeRateUpdateRequest;

import java.math.BigDecimal;
import java.util.EnumMap;

@RestController
@RequestMapping(path = "/api/currencies")
@RequiredArgsConstructor
public class ExchangeRateController implements ExchangeRateApi {

  private final ExchangeRateService service;

  @Override
  @GetMapping("/{currency}/exchangeRates")
  public EnumMap<Currency, BigDecimal> getExchangeRatesByCurrency(@PathVariable Currency currency) {
    return service.getExchangeRate(currency);
  }

  @Override
  @PostMapping("/{currency}/exchangeRates")
  public EnumMap<Currency, BigDecimal> updateExchangeRatesByCurrency(
    @PathVariable Currency currency,
    @RequestBody EnumMap<Currency, BigDecimal> currencyToRate
  ) {
    ExchangeRateUpdateRequest request = new ExchangeRateUpdateRequest(currency, currencyToRate);
    return service.updateExchangeRatesByCurrency(request);
  }
}
