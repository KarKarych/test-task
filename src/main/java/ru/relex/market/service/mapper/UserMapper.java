package ru.relex.market.service.mapper;

import org.springframework.stereotype.Component;
import ru.relex.market.db.entity.User;
import ru.relex.market.service.model.user.Role;
import ru.relex.market.service.model.user.UserDto;

import java.util.List;

@Component
public class UserMapper {

  public User toEntity(UserDto dto, String secretKey) {
    if (dto == null) {
      return null;
    }

    return User.builder()
      .secretKey(secretKey)
      .username(dto.getUsername())
      .email(dto.getEmail())
      .authorities(List.of(Role.USER))
      .build();
  }
}
