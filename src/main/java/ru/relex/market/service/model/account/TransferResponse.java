package ru.relex.market.service.model.account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TransferResponse extends TransferRequest {

  @NotNull
  @Positive
  private BigDecimal amountTo;

  public TransferResponse(TransferRequest request, BigDecimal amountTo) {
    super(request.getCurrencyFrom(), request.getCurrencyTo(), request.getAmount());
    this.amountTo = amountTo;
  }
}
