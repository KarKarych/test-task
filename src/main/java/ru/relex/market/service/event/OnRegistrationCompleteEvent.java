package ru.relex.market.service.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import ru.relex.market.db.entity.User;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

  private final User user;

  public OnRegistrationCompleteEvent(User user) {
    super(user);
    this.user = user;
  }
}
