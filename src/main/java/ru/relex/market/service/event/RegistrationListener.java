package ru.relex.market.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import ru.relex.market.db.entity.User;
import ru.relex.market.service.logic.VerificationTokenService;

@Component
@EnableAsync(proxyTargetClass = true)
@RequiredArgsConstructor
public class RegistrationListener implements AsyncConfigurer {

  private final static String ADDRESS = "localhost";

  @Value("${server.port}")
  private String port;

  private final VerificationTokenService verificationTokenService;

  private final JavaMailSender mailSender;

  @Async
  @EventListener
  public void onApplicationEvent(@NonNull OnRegistrationCompleteEvent event) {
    confirmRegistration(event);
  }

  private void confirmRegistration(OnRegistrationCompleteEvent event) {
    User user = event.getUser();
    String verifyToken = verificationTokenService.generateToken(user);
    String recipientAddress = user.getEmail();

    String subject = "Registration Confirmation";
    String confirmationUrl = "http://%s:%s/api/tokens/verify?token=%s".formatted(ADDRESS, port, verifyToken);
    String emailMessage = "Link for account activation";

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(recipientAddress);
    message.setSubject(subject);
    message.setText(emailMessage + "\r\n" + confirmationUrl);

    mailSender.send(message);
  }
}
