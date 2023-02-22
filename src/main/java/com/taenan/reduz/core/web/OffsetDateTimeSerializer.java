package com.taenan.reduz.core.web;

import java.io.IOException;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {
	@Override
	public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeNumber(value.toInstant().toEpochMilli());
	}
}