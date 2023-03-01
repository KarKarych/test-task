package ru.relex.market.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.relex.market.rest.api.UserAccountApi;
import ru.relex.market.service.logic.UserAccountService;
import ru.relex.market.service.model.account.TransferRequest;
import ru.relex.market.service.model.account.TransferResponse;
import ru.relex.market.service.model.account.UserAccountDto;
import ru.relex.market.service.model.account.WithdrawDto;
import ru.relex.market.service.model.exchange.Currency;
import ru.relex.market.service.model.exchange.CurrencyValueDto;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/accounts")
@RequiredArgsConstructor
public class UserAccountController implements UserAccountApi {

  private final UserAccountService service;

  @Override
  @GetMapping("/balance")
  public Map<String, BigDecimal> getUserAccounts() {
    return service.getUserAccounts();
  }

  @Override
  @PostMapping("/deposit")
  public UserAccountDto depositToAccount(@RequestBody UserAccountDto account) {
    return service.depositToAccount(account);
  }

  @Override
  @PostMapping("/withdraw")
  public UserAccountDto withdrawalFromAccount(@RequestBody WithdrawDto withdrawDto) {
    return service.withdrawFromAccount(withdrawDto);
  }

  @Override
  @PostMapping("/transfer")
  public TransferResponse transferToAnotherAccount(@RequestBody TransferRequest request) {
    return service.transferToAccount(request);
  }

  @Override
  @GetMapping("/totalAmount/{currency}")
  public CurrencyValueDto getTotalAmount(@PathVariable Currency currency) {
    return service.getTotalAmount(currency);
  }
}

