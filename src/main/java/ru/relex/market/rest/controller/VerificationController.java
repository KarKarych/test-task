package ru.relex.market.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.relex.market.rest.api.VerificationApi;
import ru.relex.market.service.logic.VerificationTokenService;
import ru.relex.market.service.model.user.VerificationDto;

@RestController
@RequestMapping(path = "/api/tokens")
@RequiredArgsConstructor
public class VerificationController implements VerificationApi {

  private final VerificationTokenService service;

  @Override
  @GetMapping("/verify")
  public VerificationDto verifyEmail(@RequestParam("token") String token) {
    return service.verifyToken(token);
  }
}
