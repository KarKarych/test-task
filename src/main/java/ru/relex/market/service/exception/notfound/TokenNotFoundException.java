package ru.relex.market.service.exception.notfound;

public class TokenNotFoundException extends NotFoundException {

  public TokenNotFoundException(String token) {
    super("Token %s not found".formatted(token));
  }
}
