package ru.relex.market.rest.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.relex.market.service.model.user.RegisterResponse;
import ru.relex.market.service.model.user.UserDto;

public interface UserApi {

  @PostMapping
  RegisterResponse register(@RequestBody UserDto userDto);
}
