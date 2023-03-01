package ru.relex.market.service.exception.auth;

public class RequestForbiddenException extends RuntimeException {

  public RequestForbiddenException() {
    super("Unauthorized access, invalid credentials");
  }
}
