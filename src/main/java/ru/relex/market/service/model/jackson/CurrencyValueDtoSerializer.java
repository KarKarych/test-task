package ru.relex.market.service.model.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.relex.market.service.model.exchange.CurrencyValueDto;

import java.io.IOException;

public class CurrencyValueDtoSerializer extends JsonSerializer<CurrencyValueDto> {

  @Override
  public void serialize(CurrencyValueDto dto, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeStartObject();
    gen.writeNumberField(dto.getCurrency().toString(), dto.getAmount());
    gen.writeEndObject();
  }
}
