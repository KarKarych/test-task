package ru.relex.market.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.relex.market.db.entity.UserAccount;
import ru.relex.market.db.entity.UserAccountId;
import ru.relex.market.service.model.exchange.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UserAccountId> {

  @Query("""
    select  sum(u.balance)
     from   UserAccount u
     where  u.id.currency = :currency
    """)
  BigDecimal sumBalancesByCurrency(Currency currency);

  List<UserAccount> findByIdUserId(UUID userId);
}
