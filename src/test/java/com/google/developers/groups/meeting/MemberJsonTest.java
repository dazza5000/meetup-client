package com.google.developers.groups.meeting;

import com.bitgrind.meetup.api.model.Member;

import org.junit.Test;

import java.io.IOException;

public class MemberJsonTest extends JsonTest {

    @Test
    public void testDeserialize() throws IOException {
        Member member = fromJSON("member.json", Member.class);
    }
}
