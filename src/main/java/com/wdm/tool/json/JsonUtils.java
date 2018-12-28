package com.wdm.tool.json;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.databind.type.TypeFactory.defaultInstance;

/**
 * @author wdmyong
 */
public final class JsonUtils {

    private JsonUtils() {
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String object2JsonStr(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T jsonStr2Object(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            json = "{}";
        }
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <E, T extends Collection<E>> T jsonStr2Collection(String json,
            Class<? extends Collection> collectionType, Class<E> valueType) {
        if (StringUtils.isEmpty(json)) {
            json = "[]";
        }
        try {
            return MAPPER.readValue(json,
                    defaultInstance().constructCollectionType(collectionType, valueType));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
