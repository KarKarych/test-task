package ru.relex.market.service.logic;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.relex.market.db.entity.User;
import ru.relex.market.service.model.user.VerificationDto;

@Validated
public interface VerificationTokenService {

  String generateToken(@NotNull User user);

  VerificationDto verifyToken(@NotNull String token);
}
