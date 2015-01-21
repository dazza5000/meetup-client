package com.google.developers.groups.meeting;

import com.bitgrind.meetup.api.model.AttendanceList;

import org.junit.Test;

import java.io.IOException;

public class AttendanceJsonTest extends JsonTest {

    @Test
    public void testDeserialize() throws IOException {
        AttendanceList attendance = fromJSON("attendance.json", AttendanceList.class);
    }
}
