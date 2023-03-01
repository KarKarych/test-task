package ru.relex.market.service.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.relex.market.service.validation.constraint.EmailUnique;
import ru.relex.market.service.validation.constraint.UsernameUnique;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {

  @NotNull
  @UsernameUnique
  private String username;

  @NotNull
  @Email
  @EmailUnique
  private String email;

  @JsonIgnore
  private String secretKey;
}
