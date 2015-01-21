package com.google.developers.groups.meeting;

import com.bitgrind.meetup.api.model.MemberList;

import org.junit.Test;

import java.io.IOException;

public class MemberListJsonTest extends JsonTest {

    @Test
    public void testDeserialize() throws IOException {
        MemberList list = fromJSON("member_list.json", MemberList.class);
    }
}
