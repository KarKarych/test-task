package ru.relex.market.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.relex.market.service.model.account.TransactionType;
import ru.relex.market.service.model.exchange.Currency;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "transactions_history", schema = "market_base")
@EntityListeners(AuditingEntityListener.class)
public class TransactionHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "currency_from")
  @Enumerated(EnumType.STRING)
  private Currency currencyFrom;

  @Column(name = "currency_to")
  @Enumerated(EnumType.STRING)
  private Currency currencyTo;

  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @CreatedDate
  @Column(name = "created_at")
  private Instant createdAt;
}
