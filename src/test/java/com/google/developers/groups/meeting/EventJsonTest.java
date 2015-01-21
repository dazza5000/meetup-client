package com.google.developers.groups.meeting;

import com.bitgrind.meetup.api.model.Event;

import org.junit.Test;

import java.io.IOException;

public class EventJsonTest extends JsonTest {

    @Test
    public void testDeserialize() throws IOException {
        Event event = fromJSON("event.json", Event.class);
    }
}
