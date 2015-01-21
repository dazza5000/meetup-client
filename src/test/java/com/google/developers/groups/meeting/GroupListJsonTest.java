package com.google.developers.groups.meeting;

import com.bitgrind.meetup.api.model.GroupList;

import org.junit.Test;

import java.io.IOException;

public class GroupListJsonTest extends JsonTest {
    @Test
    public void testDeserialize() throws IOException {
        GroupList list = fromJSON("group_list.json", GroupList.class);
    }

}
