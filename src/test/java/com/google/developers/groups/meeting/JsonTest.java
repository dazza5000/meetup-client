package com.google.developers.groups.meeting;

import com.bitgrind.meetup.api.MeetupClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * Created by mrenouf on 1/16/15.
 */
public class JsonTest {
    public <T> T fromJSON(String resource, Class<T> type) {
        InputStream in = getClass().getResourceAsStream(resource);
        Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        return  MeetupClient.GSON.fromJson(reader, type);
    }
}
