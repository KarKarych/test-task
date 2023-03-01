package ru.relex.market.service.model.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.relex.market.service.model.account.UserAccountDto;

import java.io.IOException;

public class UserAccountDtoSerializer extends JsonSerializer<UserAccountDto> {

  @Override
  public void serialize(UserAccountDto dto, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeStartObject();
    gen.writeNumberField(dto.getAccount(), dto.getAmount());
    gen.writeEndObject();
  }
}
