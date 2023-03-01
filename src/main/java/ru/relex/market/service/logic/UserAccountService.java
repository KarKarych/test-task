package ru.relex.market.service.logic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.relex.market.service.model.account.TransferRequest;
import ru.relex.market.service.model.account.TransferResponse;
import ru.relex.market.service.model.account.UserAccountDto;
import ru.relex.market.service.model.account.WithdrawDto;
import ru.relex.market.service.model.exchange.Currency;
import ru.relex.market.service.model.exchange.CurrencyValueDto;

import java.math.BigDecimal;
import java.util.Map;

@Validated
public interface UserAccountService {

  Map<String, BigDecimal> getUserAccounts();

  UserAccountDto depositToAccount(@NotNull @Valid UserAccountDto account);

  UserAccountDto withdrawFromAccount(@NotNull @Valid WithdrawDto withdrawDto);

  TransferResponse transferToAccount(@NotNull @Valid TransferRequest request);

  CurrencyValueDto getTotalAmount(@NotNull Currency currency);
}
