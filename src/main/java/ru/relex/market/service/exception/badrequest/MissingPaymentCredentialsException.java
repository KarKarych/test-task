package ru.relex.market.service.exception.badrequest;

public class MissingPaymentCredentialsException extends BadRequestException {

  public MissingPaymentCredentialsException() {
    super("Request must contain the details of the card or the wallet");
  }
}
