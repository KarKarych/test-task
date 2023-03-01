package ru.relex.market.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.relex.market.service.model.account.TransferRequest;
import ru.relex.market.service.model.account.TransferResponse;
import ru.relex.market.service.model.account.UserAccountDto;
import ru.relex.market.service.model.account.WithdrawDto;
import ru.relex.market.service.model.exchange.Currency;
import ru.relex.market.service.model.exchange.CurrencyValueDto;

import java.math.BigDecimal;
import java.util.Map;

public interface UserAccountApi {

  @Operation(security = {@SecurityRequirement(name = "secret_key")})
  @GetMapping("/balance")
  Map<String, BigDecimal> getUserAccounts();

  @Operation(security = {@SecurityRequirement(name = "secret_key")})
  @PostMapping("/deposit")
  UserAccountDto depositToAccount(@RequestBody UserAccountDto account);

  @Operation(security = {@SecurityRequirement(name = "secret_key")})
  @PostMapping("/withdraw")
  UserAccountDto withdrawalFromAccount(@RequestBody WithdrawDto withdrawDto);

  @Operation(security = {@SecurityRequirement(name = "secret_key")})
  @PostMapping("/transfer")
  TransferResponse transferToAnotherAccount(@RequestBody TransferRequest request);

  @Operation(security = {@SecurityRequirement(name = "secret_key")})
  @GetMapping("/totalAmount/{currency}")
  CurrencyValueDto getTotalAmount(@PathVariable Currency currency);
}
