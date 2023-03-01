package ru.relex.market.service.logic.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.relex.market.db.entity.ExchangeRate;
import ru.relex.market.db.entity.UserAccount;
import ru.relex.market.db.entity.UserAccountId;
import ru.relex.market.db.repository.UserAccountRepository;
import ru.relex.market.security.SecurityService;
import ru.relex.market.service.exception.badrequest.InsufficientFundsException;
import ru.relex.market.service.exception.badrequest.MissingPaymentCredentialsException;
import ru.relex.market.service.logic.ExchangeRateService;
import ru.relex.market.service.logic.TransactionHistoryService;
import ru.relex.market.service.logic.UserAccountService;
import ru.relex.market.service.mapper.UserAccountMapper;
import ru.relex.market.service.model.account.*;
import ru.relex.market.service.model.exchange.Currency;
import ru.relex.market.service.model.exchange.CurrencyValueDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static ru.relex.market.service.model.account.TransactionType.*;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

  private final UserAccountMapper mapper;

  private final UserAccountRepository repository;

  private final SecurityService securityService;

  private final TransactionHistoryService historyService;

  private final ExchangeRateService exchangeService;

  @Override
  public Map<String, BigDecimal> getUserAccounts() {
    UUID userId = securityService.getId();
    List<UserAccount> accounts = repository.findByIdUserId(userId);
    return mapper.toAccountBalanceMap(accounts);
  }

  @Override
  @Transactional
  public UserAccountDto depositToAccount(UserAccountDto accountDto) {
    Currency currency = accountDto.getCurrency();
    BigDecimal depositAmount = accountDto.getAmount();
    UserAccount updatedAccount = depositToAccount(currency, depositAmount);

    saveTransactionToHistory(currency, currency, depositAmount, DEPOSIT);

    return mapper.fromEntity(updatedAccount);
  }

  private UserAccount depositToAccount(Currency currency, BigDecimal depositAmount) {
    UserAccount account = findUserAccount(currency);

    BigDecimal balance = account.getBalance();
    BigDecimal updatedBalance = balance.add(depositAmount);
    account.setBalance(updatedBalance);

    return repository.save(account);
  }

  @Override
  @Transactional
  public UserAccountDto withdrawFromAccount(WithdrawDto withdrawDto) {
    validateCredentials(withdrawDto);

    Currency currency = withdrawDto.getCurrency();
    BigDecimal withdrawalAmount = withdrawDto.getCount();
    UserAccount updatedAccount = withdrawFromAccount(currency, withdrawalAmount);

    saveTransactionToHistory(currency, currency, withdrawalAmount, WITHDRAW);

    return mapper.fromEntity(updatedAccount);
  }

  private UserAccount withdrawFromAccount(Currency currency, BigDecimal withdrawalAmount) {
    UserAccount account = findUserAccount(currency);

    BigDecimal balance = account.getBalance();
    if (balance.compareTo(withdrawalAmount) < 0) {
      throw new InsufficientFundsException(balance, currency);
    }
    BigDecimal updatedBalance = balance.subtract(withdrawalAmount);
    account.setBalance(updatedBalance);

    return repository.save(account);
  }

  private void validateCredentials(WithdrawDto withdrawDto) {
    String creditCard = withdrawDto.getCreditCard();
    String wallet = withdrawDto.getWallet();
    if (creditCard == null && wallet == null || creditCard != null && wallet != null) {
      throw new MissingPaymentCredentialsException();
    }
  }

  private UserAccount findUserAccount(Currency currency) {
    UUID userId = securityService.getId();
    UserAccountId accountId = new UserAccountId(userId, currency);
    return repository.findById(accountId)
      .orElseGet(() -> new UserAccount(accountId, BigDecimal.ZERO));
  }

  @Override
  @Transactional
  public TransferResponse transferToAccount(TransferRequest request) {
    Currency currencyFrom = request.getCurrencyFrom();
    Currency currencyTo = request.getCurrencyTo();
    BigDecimal transferAmount = request.getAmount();

    ExchangeRate exchangeRate = exchangeService.getExchangeRate(currencyFrom, currencyTo);

    withdrawFromAccount(currencyFrom, transferAmount);

    BigDecimal transferredAmount = transferAmount.multiply(exchangeRate.getRate());
    depositToAccount(currencyTo, transferredAmount);

    saveTransactionToHistory(currencyFrom, currencyTo, transferAmount, TRANSFER);

    return new TransferResponse(request, transferredAmount);
  }

  @Override
  public CurrencyValueDto getTotalAmount(Currency currency) {
    return new CurrencyValueDto(currency, repository.sumBalancesByCurrency(currency));
  }

  private void saveTransactionToHistory(
    Currency currencyFrom, Currency currencyTo,
    BigDecimal amount,
    TransactionType type
  ) {
    UUID userId = securityService.getId();
    var historyDto = new TransactionHistoryDto(userId, currencyFrom, currencyTo, amount, type);
    historyService.saveTransactionToHistory(historyDto);
  }
}
