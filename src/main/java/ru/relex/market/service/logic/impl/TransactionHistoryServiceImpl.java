package ru.relex.market.service.logic.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.relex.market.db.repository.TransactionHistoryRepository;
import ru.relex.market.service.logic.TransactionHistoryService;
import ru.relex.market.service.mapper.TransactionHistoryMapper;
import ru.relex.market.service.model.account.Interval;
import ru.relex.market.service.model.account.TransactionCount;
import ru.relex.market.service.model.account.TransactionHistoryDto;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

  private final TransactionHistoryRepository repository;

  private final TransactionHistoryMapper mapper;

  @Override
  public TransactionCount getTransactionCount(Interval interval) {
    Instant dateFrom = interval.getDateFromAsInstant();
    Instant dateTo = interval.getDateToAsInstant();
    Long transactionCount = repository.sumTransactionsByInterval(dateFrom, dateTo);
    return new TransactionCount(transactionCount == null ? 0 : transactionCount);
  }

  @Override
  public void saveTransactionToHistory(TransactionHistoryDto historyDto) {
    repository.save(mapper.toEntity(historyDto));
  }
}
