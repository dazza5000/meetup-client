package com.google.developers.groups.meeting;

import com.bitgrind.meetup.api.model.Group;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by mrenouf on 12/31/14.
 */
public class GroupJsonTest extends JsonTest {

    @Test
    public void testDeserialize() throws IOException {
        Group group = fromJSON("group.json", Group.class);
    }
}
