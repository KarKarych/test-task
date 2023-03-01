package ru.relex.market.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.relex.market.security.SecretAuthenticationFilter;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final SecretAuthenticationFilter authenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .authorizeHttpRequests()
      .requestMatchers(HttpMethod.GET, "/api/accounts/totalAmount/*").hasAuthority("ADMIN")
      .requestMatchers(HttpMethod.POST, "/api/currencies/*/exchangeRates").hasAuthority("ADMIN")
      .requestMatchers("/api/history/**").hasAuthority("ADMIN")
      .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
      .requestMatchers(HttpMethod.GET, "/api/tokens/verify").permitAll()
      .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/api-docs/**").permitAll()
      .anyRequest().authenticated()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .exceptionHandling()
      .authenticationEntryPoint((req, res, ex) -> res.sendError(SC_UNAUTHORIZED, "Access Denied"))
      .and()
      .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
