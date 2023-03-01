package ru.relex.market.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.relex.market.db.repository.UserRepository;
import ru.relex.market.service.validation.constraint.UsernameUnique;

@RequiredArgsConstructor
public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, String> {

  private final UserRepository repository;

  @Override
  public boolean isValid(String username, ConstraintValidatorContext context) {
    if (username == null) {
      return true;
    }

    return !repository.existsByUsername(username);
  }
}
