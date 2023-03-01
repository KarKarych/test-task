package ru.relex.market.service.model.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VerificationDto {

  private final static String DEFAULT_MESSAGE = "Thank you for registering for our service. " +
    "Your account has been successfully created. Now you can use our API";

  @NotNull
  private String message;

  public VerificationDto() {
    this.message = DEFAULT_MESSAGE;
  }
}
