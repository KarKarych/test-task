package ru.relex.market.service.logic.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.relex.market.db.entity.ExchangeRate;
import ru.relex.market.db.entity.ExchangeRateId;
import ru.relex.market.db.repository.ExchangeRateRepository;
import ru.relex.market.service.exception.badrequest.MissingCurrencyException;
import ru.relex.market.service.exception.badrequest.SameTransferAccountException;
import ru.relex.market.service.exception.badrequest.ValueLessOrEqualsToZeroException;
import ru.relex.market.service.exception.internalerror.InternalServerErrorException;
import ru.relex.market.service.logic.ExchangeRateService;
import ru.relex.market.service.mapper.ExchangeRateMapper;
import ru.relex.market.service.model.exchange.Currency;
import ru.relex.market.service.model.exchange.ExchangeRateUpdateRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;


@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private final ExchangeRateRepository repository;

  private final ExchangeRateMapper mapper;

  @Override
  public EnumMap<Currency, BigDecimal> getExchangeRate(Currency currency) {
    List<ExchangeRate> exchangeRates = repository.findByIdCurrencyFrom(currency);
    return mapper.toCurrencyRatesMap(exchangeRates);
  }

  @Override
  public EnumMap<Currency, BigDecimal> updateExchangeRatesByCurrency(ExchangeRateUpdateRequest request) {
    Currency baseCur = request.getBaseCurrency();
    EnumMap<Currency, BigDecimal> currencyToRateMap = request.getCurrencyToRate();

    validateCurrenciesCount(currencyToRateMap, baseCur);

    List<ExchangeRate> entitiesToSave = new ArrayList<>();
    for (var currencyToRate : currencyToRateMap.entrySet()) {
      Currency secondCur = currencyToRate.getKey();
      BigDecimal secondRate = currencyToRate.getValue();

      entitiesToSave.add(mapper.toEntity(baseCur, secondCur, secondRate));
      entitiesToSave.add(mapper.toEntity(secondCur, baseCur, ONE.divide(secondRate, 18, HALF_UP)));
    }

    repository.saveAll(entitiesToSave);

    return getExchangeRate(baseCur);
  }

  private void validateCurrenciesCount(EnumMap<Currency, BigDecimal> currencyToRateMap, Currency baseCurrency) {
    for (var currencyToRate : currencyToRateMap.entrySet()) {
      Currency cur = currencyToRate.getKey();
      BigDecimal rate = currencyToRate.getValue();

      if (rate.compareTo(ZERO) <= 0) {
        throw new ValueLessOrEqualsToZeroException("%s rate %s is less or equal to zero".formatted(cur, rate));
      }
    }

    EnumSet<Currency> currencies = EnumSet.allOf(Currency.class);
    currencies.remove(baseCurrency);
    currencies.removeAll(currencyToRateMap.keySet());

    if (currencies.size() != 0) {
      String missingCurrencies = currencies.toString().replaceAll("[\\[\\],]", "");
      throw new MissingCurrencyException(missingCurrencies);
    }
  }

  @Override
  public ExchangeRate getExchangeRate(Currency currencyFrom, Currency currencyTo) {
    if (currencyFrom.equals(currencyTo)) {
      throw new SameTransferAccountException();
    }

    return repository.findById(new ExchangeRateId(currencyFrom, currencyTo))
      .orElseThrow(() -> new InternalServerErrorException("Required exchange rate not found"));
  }
}
