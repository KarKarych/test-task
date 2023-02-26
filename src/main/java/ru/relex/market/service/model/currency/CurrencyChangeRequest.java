package ru.relex.market.service.model.currency;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import ru.relex.market.service.jackson.CurrencyChangeRequestDeserializer;
import ru.relex.market.service.model.SecretKeyDto;

import java.math.BigDecimal;
import java.util.EnumMap;

@Getter
@SuperBuilder
@JsonDeserialize(using = CurrencyChangeRequestDeserializer.class)
public class CurrencyChangeRequest extends SecretKeyDto {

  private final CurrencyCode baseCurrencyCode;

  private final EnumMap<CurrencyCode, BigDecimal> currencyToRate;

}
