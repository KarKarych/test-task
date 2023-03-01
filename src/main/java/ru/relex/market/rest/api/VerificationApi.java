package ru.relex.market.rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.relex.market.service.model.user.VerificationDto;

public interface VerificationApi {

  @GetMapping("/verify")
  VerificationDto verifyEmail(@RequestParam("token") String token);
}
