package com.taenan.reduz.core.web;

import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {
	@Override
	public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		long epochMillis = p.getLongValue();
		Instant instant = Instant.ofEpochMilli(epochMillis);
		return OffsetDateTime.ofInstant(instant, ZoneId.systemDefault());
	}
}