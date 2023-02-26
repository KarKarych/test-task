package ru.relex.market.service.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.relex.market.service.exception.BadRequestException;
import ru.relex.market.service.exception.currency.MissingCurrencyException;
import ru.relex.market.service.exception.currency.UnsupportedCurrencyCodeException;
import ru.relex.market.service.exception.currency.UnsupportedCurrencyRateException;
import ru.relex.market.service.model.currency.CurrencyChangeRequest;
import ru.relex.market.service.model.currency.CurrencyCode;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Optional;

public class CurrencyChangeRequestDeserializer extends JsonDeserializer<CurrencyChangeRequest> {

  private final static String BASE_CURRENCY_FIELD_NAME = "base_currency";
  private final static String SECRET_KEY_FIELD_NAME = "secret_key";

  @Override
  public CurrencyChangeRequest deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
    JsonNode jsonNode = jp.getCodec().readTree(jp);
    JsonNode secretKeyNode = jsonNode.get(SECRET_KEY_FIELD_NAME);
    JsonNode baseCurrencyNode = jsonNode.get(BASE_CURRENCY_FIELD_NAME);

    String secretKey = Optional.ofNullable(secretKeyNode)
      .map(JsonNode::asText)
      .orElseThrow(() -> new BadRequestException("Missing secret_key field"));

    CurrencyCode baseCurrency = Optional.ofNullable(baseCurrencyNode)
      .map(JsonNode::asText)
      .map(this::parseCurrencyCode)
      .orElseThrow(() -> new BadRequestException("Missing base_currency field"));

    EnumMap<CurrencyCode, BigDecimal> currencyToRate = new EnumMap<>(CurrencyCode.class);
    for (CurrencyCode code : CurrencyCode.values()) {
      BigDecimal rate = getCurrencyRate(jsonNode, code);
      if (rate != null) {
        currencyToRate.put(code, rate);
      }
    }

    validateCurrenciesCount(currencyToRate, baseCurrency);

    return CurrencyChangeRequest.builder()
      .secretKey(secretKey)
      .baseCurrencyCode(baseCurrency)
      .currencyToRate(currencyToRate)
      .build();
  }

  private CurrencyCode parseCurrencyCode(String currencyCodeString) {
    return CurrencyCode.fromString(currencyCodeString)
      .orElseThrow(() -> new UnsupportedCurrencyCodeException("Invalid currency code: " + currencyCodeString));
  }

  private BigDecimal getCurrencyRate(JsonNode node, CurrencyCode code) {
    JsonNode rateNode = node.get(code.toString());
    if (rateNode == null) {
      return null;
    } else {
      return parseCurrencyRate(rateNode.asText(), code);
    }
  }

  private BigDecimal parseCurrencyRate(String currencyRateString, CurrencyCode code) {
    try {
      return new BigDecimal(currencyRateString);
    } catch (NumberFormatException e) {
      throw new UnsupportedCurrencyRateException("Invalid %s rate: %s".formatted(code, currencyRateString));
    }
  }

  private void validateCurrenciesCount(EnumMap<CurrencyCode, BigDecimal> currencyToRate, CurrencyCode baseCurrency) {
    EnumSet<CurrencyCode> currencyCodes = EnumSet.allOf(CurrencyCode.class);
    currencyCodes.remove(baseCurrency);
    currencyCodes.removeAll(currencyToRate.keySet());

    if (currencyCodes.size() != 0) {
      String missingCurrencies = currencyCodes.toString().replaceAll("[\\[\\],]", "");
      throw new MissingCurrencyException("Missing following currencies: %s".formatted(missingCurrencies));
    }
  }
}
