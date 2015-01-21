package com.google.developers.groups.meeting;

import com.bitgrind.meetup.api.gson.LowerCaseEnumTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LowerCaseEnumTypeAdapterFactoryTest {
    enum TestEnum {
        VALUE_A,
        VALUE_B;
    }

    @Test
    public void testEnumMapping() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new LowerCaseEnumTypeAdapterFactory())
                .create();

        String stringValue = gson.toJson(TestEnum.VALUE_A);
        assertThat(stringValue, is(equalTo("\"value_a\"")));

        TestEnum enumValue = gson.fromJson("\"value_b\"", TestEnum.class);
        assertThat(enumValue, is(equalTo(TestEnum.VALUE_B)));
    }
}
