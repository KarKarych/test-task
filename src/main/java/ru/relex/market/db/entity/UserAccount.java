package ru.relex.market.db.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "user_accounts", schema = "market_base")
public class UserAccount {

  @EmbeddedId
  private UserAccountId id;

  private BigDecimal balance;

  public Currency getCurrency() {
    return id.getCurrency();
  }

  public String getCurrencyAsString() {
    return id.getCurrency().toString();
  }
}
