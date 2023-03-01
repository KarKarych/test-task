package ru.relex.market.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.relex.market.db.entity.User;
import ru.relex.market.service.exception.auth.RequestForbiddenException;

import java.util.UUID;

@Service
public class SecurityService {

  public UUID getId() {
    SecurityContext context = SecurityContextHolder.getContext();
    Object principal = context.getAuthentication().getPrincipal();
    if (principal instanceof User user) {
      return user.getId();
    } else {
      throw new RequestForbiddenException();
    }
  }
}
