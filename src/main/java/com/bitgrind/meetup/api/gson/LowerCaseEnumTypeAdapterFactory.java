package com.bitgrind.meetup.api.gson;

import com.bitgrind.meetup.api.model.Rsvp;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class LowerCaseEnumTypeAdapterFactory implements TypeAdapterFactory {

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (!rawType.isEnum()) {
            return null;
        }

        final Class<? extends Enum> enumClass = (Class<? extends Enum>) rawType;
        final Map<String, Enum> map = new HashMap<>();
        for (Enum constant: enumClass.getEnumConstants()) {
            map.put(constant.name().toLowerCase(), constant);
        }

        return new TypeAdapter<T>() {
            public void write(JsonWriter out, T value) throws IOException {
                if (value == null) {
                    out.nullValue();
                } else {
                    out.value(((Enum) value).name().toLowerCase());
                }
            }

            public T read(JsonReader reader) throws IOException {
                JsonToken nextToken = reader.peek();
                T result = null;
                if (nextToken == JsonToken.STRING) {
                    result = (T) Enum.valueOf(enumClass, reader.nextString().toUpperCase());
                } else {
                    reader.skipValue();
                }
                return result;
            }
        };
    }
}
