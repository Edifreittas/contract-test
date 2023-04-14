package br.com.spam.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {

    }

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.debug("Error trying to parse object to JSON. Object: {}", ( obj == null ? "null" : obj.toString()), e);
            return null;
        }
    }

    public static <T> T parseJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.debug("Error trying to parse JSON to object {}. Payload: {}",
                    (clazz == null ? "null" : clazz.getName()),
                    (json == null ? "null" : json),
                    e);
            return null;
        }
    }

}
