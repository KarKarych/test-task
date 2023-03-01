package ru.relex.market.service.model.account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.relex.market.service.model.exchange.Currency;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

  @NotNull
  private Currency currencyFrom;

  @NotNull
  private Currency currencyTo;

  @NotNull
  @Positive
  private BigDecimal amount;
}
