package ru.relex.market.service.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.relex.market.service.validation.validator.UsernameUniqueValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameUniqueValidator.class)
public @interface UsernameUnique {

  String message() default "already exists";

  Class<? extends Payload>[] payload() default {};

  Class<?>[] groups() default {};
}
