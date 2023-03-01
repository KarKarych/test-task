package ru.relex.market.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import ru.relex.market.service.model.exchange.Currency;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class ExchangeRateId implements Serializable {

  @Column(name = "currency_from")
  @Enumerated(EnumType.STRING)
  private Currency currencyFrom;

  @Column(name = "currency_to")
  @Enumerated(EnumType.STRING)
  private Currency currencyTo;
}
