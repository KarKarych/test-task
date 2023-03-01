package ru.relex.market.service.model.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.relex.market.service.model.account.UserAccountDto;
import ru.relex.market.service.model.exchange.Currency;

import java.io.IOException;
import java.math.BigDecimal;


public class UserAccountDtoDeserializer extends JsonDeserializer<UserAccountDto> {

  @Override
  public UserAccountDto deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
    jp.nextToken();
    String account = jp.getText();
    jp.nextToken();
    BigDecimal amount = jp.getDecimalValue();

    var currencyO = Currency.fromStringToCurrency(account.substring(0, 3));
    Currency currency = currencyO.orElse(null);
    return new UserAccountDto(currency + "_wallet", currency, amount);
  }
}

