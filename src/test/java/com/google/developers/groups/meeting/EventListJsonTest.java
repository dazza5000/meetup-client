package com.google.developers.groups.meeting;

import com.bitgrind.meetup.api.model.EventList;

import org.junit.Test;

import java.io.IOException;

public class EventListJsonTest extends JsonTest {

    @Test
    public void testDeserialize() throws IOException {
        EventList list = fromJSON("event_list.json", EventList.class);
    }
}
