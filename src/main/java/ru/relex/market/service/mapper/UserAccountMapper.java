package ru.relex.market.service.mapper;

import org.springframework.stereotype.Component;
import ru.relex.market.db.entity.UserAccount;
import ru.relex.market.service.model.account.UserAccountDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserAccountMapper {

  public Map<String, BigDecimal> toAccountBalanceMap(List<UserAccount> accounts) {
    if (accounts == null) {
      return null;
    }

    return accounts.stream()
      .collect(Collectors.toMap(
        userAccount -> userAccount.getCurrencyAsString() + "_wallet",
        UserAccount::getBalance
      ));
  }

  public UserAccountDto fromEntity(UserAccount userAccount) {
    if (userAccount == null) {
      return null;
    }

    return new UserAccountDto(
      userAccount.getCurrency(),
      userAccount.getBalance()
    );
  }
}
