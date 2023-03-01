package ru.relex.market.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.market.db.entity.ExchangeRate;
import ru.relex.market.db.entity.ExchangeRateId;
import ru.relex.market.service.model.exchange.Currency;

import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, ExchangeRateId> {

  List<ExchangeRate> findByIdCurrencyFrom(Currency code);
}
