package ru.relex.market.service.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.relex.market.service.model.exchange.Currency;
import ru.relex.market.service.model.jackson.UserAccountDtoDeserializer;
import ru.relex.market.service.model.jackson.UserAccountDtoSerializer;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = UserAccountDtoSerializer.class)
@JsonDeserialize(using = UserAccountDtoDeserializer.class)
public class UserAccountDto {

  @NotNull
  private String account;

  @JsonIgnore
  @NotNull
  private Currency currency;

  @NotNull
  @PositiveOrZero
  private BigDecimal amount;

  public UserAccountDto(Currency currency, BigDecimal amount) {
    this.account = currency.toString() + "_wallet";
    this.amount = amount;
  }
}
