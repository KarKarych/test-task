package ru.relex.market.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.relex.market.rest.api.UserApi;
import ru.relex.market.service.logic.UserService;
import ru.relex.market.service.model.user.RegisterResponse;
import ru.relex.market.service.model.user.UserDto;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController implements UserApi {

  private final UserService service;

  @Override
  @PostMapping
  public RegisterResponse register(@RequestBody UserDto userDto) {
    return service.register(userDto);
  }
}
