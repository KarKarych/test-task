package ru.relex.market.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.relex.market.service.model.account.Interval;
import ru.relex.market.service.model.account.TransactionCount;

public interface TransactionHistoryApi {

  @Operation(security = {@SecurityRequirement(name = "secret_key")})
  @GetMapping("/transactionCount")
  TransactionCount getTransactionCount(@RequestBody Interval interval);
}
