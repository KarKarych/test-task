package ru.relex.market.db.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.relex.market.service.model.exchange.Currency;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "exchange_rates", schema = "market_base")
public class ExchangeRate {

  @EmbeddedId
  private ExchangeRateId id;

  private BigDecimal rate;

  public Currency getCurrencyTo() {
    return id.getCurrencyTo();
  }
}
