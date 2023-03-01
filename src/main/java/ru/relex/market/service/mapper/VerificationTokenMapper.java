package ru.relex.market.service.mapper;

import org.springframework.stereotype.Component;
import ru.relex.market.db.entity.User;
import ru.relex.market.db.entity.VerificationToken;

@Component
public class VerificationTokenMapper {

  public VerificationToken toEntity(String verifyToken, User user) {
    return VerificationToken.builder()
      .token(verifyToken)
      .user(user)
      .build();
  }
}
