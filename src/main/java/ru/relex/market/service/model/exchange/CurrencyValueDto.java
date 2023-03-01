package ru.relex.market.service.model.exchange;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.relex.market.service.model.jackson.CurrencyValueDtoSerializer;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = CurrencyValueDtoSerializer.class)
public class CurrencyValueDto {

  @NotNull
  private Currency currency;

  @NotNull
  @PositiveOrZero
  private BigDecimal amount;
}
