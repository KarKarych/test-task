package ru.relex.market.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import ru.relex.market.service.model.exchange.Currency;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class UserAccountId implements Serializable {

  @Column(name = "user_id")
  private UUID userId;

  @Enumerated(EnumType.STRING)
  private Currency currency;
}
