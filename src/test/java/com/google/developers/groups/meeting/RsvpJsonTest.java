package com.google.developers.groups.meeting;

import com.bitgrind.meetup.api.model.Rsvp;

import org.junit.Test;

import java.io.IOException;

import static com.bitgrind.meetup.api.model.Rsvp.Response.NO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class RsvpJsonTest extends JsonTest {

    @Test
    public void testDeserialize() throws IOException {
        Rsvp rsvp = fromJSON("rsvp.json", Rsvp.class);

        assertThat(rsvp.response, is(NO));
        assertThat(rsvp.memberPhoto, is(notNullValue()));
        assertThat(rsvp.venue, is(notNullValue()));
        assertThat(rsvp.group, is(notNullValue()));
        assertThat(rsvp.tallies, is(notNullValue()));
    }

}
