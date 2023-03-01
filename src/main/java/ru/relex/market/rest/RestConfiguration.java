package ru.relex.market.rest;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "ru.relex.market.rest")
public class RestConfiguration implements WebMvcConfigurer {

  @Override
  public void addFormatters(@NonNull FormatterRegistry registry) {
    ApplicationConversionService.configure(registry);
  }
}
