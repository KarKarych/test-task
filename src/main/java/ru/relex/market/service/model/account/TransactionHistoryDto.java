package ru.relex.market.service.model.account;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.relex.market.service.model.exchange.Currency;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryDto {

  @NotNull
  private UUID userId;

  @NotNull
  private Currency currencyFrom;

  @NotNull
  private Currency currencyTo;

  @NotNull
  private BigDecimal amount;

  @NotNull
  private TransactionType type;
}
