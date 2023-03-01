package ru.relex.market.service.logic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.relex.market.service.model.account.Interval;
import ru.relex.market.service.model.account.TransactionCount;
import ru.relex.market.service.model.account.TransactionHistoryDto;

@Validated
public interface TransactionHistoryService {

  TransactionCount getTransactionCount(@NotNull @Valid Interval interval);

  void saveTransactionToHistory(@NotNull @Valid TransactionHistoryDto historyDto);
}
