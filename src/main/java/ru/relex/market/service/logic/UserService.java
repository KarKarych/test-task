package ru.relex.market.service.logic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.relex.market.db.entity.User;
import ru.relex.market.service.model.user.RegisterResponse;
import ru.relex.market.service.model.user.UserDto;

@Validated
public interface UserService {

  RegisterResponse register(@NotNull @Valid UserDto userDto);

  User getBySecretKey(@NotNull String secretKey);
}
