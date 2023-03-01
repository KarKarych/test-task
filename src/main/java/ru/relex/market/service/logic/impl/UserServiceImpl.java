package ru.relex.market.service.logic.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.relex.market.db.entity.User;
import ru.relex.market.db.repository.UserRepository;
import ru.relex.market.service.event.OnRegistrationCompleteEvent;
import ru.relex.market.service.logic.UserService;
import ru.relex.market.service.mapper.UserMapper;
import ru.relex.market.service.model.user.RegisterResponse;
import ru.relex.market.service.model.user.UserDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final ApplicationEventPublisher eventPublisher;

  private final UserRepository repository;

  private final UserMapper mapper;

  @Override
  public RegisterResponse register(UserDto userDto) {
    String secretKey = RandomStringUtils.random(35, true, true);

    User user = repository.save(mapper.toEntity(userDto, secretKey));

    eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));

    return new RegisterResponse(secretKey);
  }

  @Override
  public User getBySecretKey(String secretKey) {
    return repository.getBySecretKey(secretKey);
  }
}
