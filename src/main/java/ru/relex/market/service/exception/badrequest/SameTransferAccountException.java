package ru.relex.market.service.exception.badrequest;

public class SameTransferAccountException extends BadRequestException {

  public SameTransferAccountException() {
    super("Transfer account is the same as the source account");
  }
}
