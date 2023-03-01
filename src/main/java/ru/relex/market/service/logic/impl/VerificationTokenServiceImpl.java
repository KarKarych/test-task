package ru.relex.market.service.logic.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import ru.relex.market.db.entity.User;
import ru.relex.market.db.repository.VerificationTokenRepository;
import ru.relex.market.service.exception.notfound.TokenNotFoundException;
import ru.relex.market.service.logic.VerificationTokenService;
import ru.relex.market.service.mapper.VerificationTokenMapper;
import ru.relex.market.service.model.user.VerificationDto;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

  private final VerificationTokenRepository repository;

  private final VerificationTokenMapper mapper;

  @Override
  public String generateToken(User user) {
    String verifyToken = RandomStringUtils.random(32, true, true);

    repository.save(mapper.toEntity(verifyToken, user));

    return verifyToken;
  }

  @Override
  @Transactional
  public VerificationDto verifyToken(String token) {
    User user = repository.findUserByToken(token)
      .orElseThrow(() -> new TokenNotFoundException(token));

    user.enable();

    repository.deleteById(token);

    return new VerificationDto();
  }
}
