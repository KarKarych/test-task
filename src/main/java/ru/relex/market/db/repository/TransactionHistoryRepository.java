package ru.relex.market.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.relex.market.db.entity.TransactionHistory;

import java.time.Instant;
import java.time.LocalDate;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, LocalDate> {

  @Query("""
    select  count(h.id)
     from   TransactionHistory h
     where  h.createdAt >= :start and h.createdAt <= :end
    """)
  Long sumTransactionsByInterval(Instant start, Instant end);
}
