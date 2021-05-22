package com.akg.catalog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {

    }

    public static <Type> Type jsonToObject(final String jsonAsString, final Class<Type> destinationClass) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(jsonAsString.replaceAll("\\\\", ""), destinationClass);
    }

    public static String objectToJson(Object object) throws IOException {
        StringWriter writer = new StringWriter();
        OBJECT_MAPPER.writeValue(writer, object);
        return writer.getBuffer().toString();
    }
}
