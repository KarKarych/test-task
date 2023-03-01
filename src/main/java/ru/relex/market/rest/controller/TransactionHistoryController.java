package ru.relex.market.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.relex.market.rest.api.TransactionHistoryApi;
import ru.relex.market.service.logic.TransactionHistoryService;
import ru.relex.market.service.model.account.Interval;
import ru.relex.market.service.model.account.TransactionCount;

@RestController
@RequestMapping(path = "/api/history")
@RequiredArgsConstructor
public class TransactionHistoryController implements TransactionHistoryApi {

  private final TransactionHistoryService service;

  @Override
  @GetMapping("/transactionCount")
  public TransactionCount getTransactionCount(@RequestBody Interval interval) {
    return service.getTransactionCount(interval);
  }
}
