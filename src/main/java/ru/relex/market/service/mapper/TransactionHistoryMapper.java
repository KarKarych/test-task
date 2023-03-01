package ru.relex.market.service.mapper;

import org.springframework.stereotype.Component;
import ru.relex.market.db.entity.TransactionHistory;
import ru.relex.market.service.model.account.TransactionHistoryDto;

@Component
public class TransactionHistoryMapper {

  public TransactionHistory toEntity(TransactionHistoryDto dto) {
    if (dto == null) {
      return null;
    }

    return TransactionHistory.builder()
      .userId(dto.getUserId())
      .currencyFrom(dto.getCurrencyFrom())
      .currencyTo(dto.getCurrencyTo())
      .amount(dto.getAmount())
      .type(dto.getType())
      .build();
  }
}
