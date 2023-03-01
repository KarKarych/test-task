package ru.relex.market.service.model.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

@Getter
@Setter
public class Interval {

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate dateFrom;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate dateTo;

  @JsonIgnore
  public Instant getDateFromAsInstant() {
    return dateFrom.atStartOfDay().toInstant(UTC);
  }

  @JsonIgnore
  public Instant getDateToAsInstant() {
    return dateTo.atStartOfDay().plusDays(1).toInstant(UTC);
  }
}
