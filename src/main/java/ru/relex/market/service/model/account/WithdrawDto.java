package ru.relex.market.service.model.account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;
import ru.relex.market.service.model.exchange.Currency;

import java.math.BigDecimal;

@Getter
@Setter
public class WithdrawDto {

  @NotNull
  private Currency currency;

  @NotNull
  @Positive
  private BigDecimal count;

  @CreditCardNumber(
    message = "Wrong card number",
    ignoreNonDigitCharacters = true
  )
  private String creditCard;

  @Size(
    message = "Length of the wallet should be 26-35 characters",
    min = 26,
    max = 35
  )
  private String wallet;
}
