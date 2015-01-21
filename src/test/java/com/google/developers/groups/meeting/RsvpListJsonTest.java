package com.google.developers.groups.meeting;

import com.bitgrind.meetup.api.model.EventSurveyAnswer;
import com.bitgrind.meetup.api.model.Rsvp;
import com.bitgrind.meetup.api.model.RsvpList;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class RsvpListJsonTest extends JsonTest {

    /**
     * RSVP list with answers as string array (the default).
     */
    @Test
    public void testDeserialize() throws IOException {
        RsvpList list = fromJSON("rsvp_list.json", RsvpList.class);
        System.out.println(list.results);
        assertThat(list.results, is(notNullValue()));
        assertThat(list.results.size(), is(5));

        Rsvp rsvp = list.results.get(0);
        assertThat(rsvp.answers, is(notNullValue()));
        assertThat(rsvp.answers.size(), is(1));

        // Verify EventSurveyAnswerDeserializer (normalize string to EventSurveyAnswer)
        assertThat(rsvp.answers.get(0).question, is(nullValue()));
        assertThat(rsvp.answers.get(0).questionId, is(0L));
        assertThat(rsvp.answers.get(0).answer, is("Expert"));
        assertThat(rsvp.answers.get(0).updated, is(0L));
    }

    /**
     * RSVP list retrieved using "fields=answer_info" with answers as objects (EventSurveyAnswer).
     */
    @Test
    public void testDeserializeAnswerInfo() throws IOException {
        RsvpList list = fromJSON("rsvp_list_answer_info.json", RsvpList.class);
        System.out.println(list.results);
        assertThat(list.results, is(notNullValue()));
        assertThat(list.results.size(), is(5));

        Rsvp rsvp = list.results.get(0);
        assertThat(rsvp.answers, is(notNullValue()));
        assertThat(rsvp.answers.size(), is(1));

        // Verify EventSurveyAnswerDeserializer
        EventSurveyAnswer eventSurveyAnswer = rsvp.answers.get(0);
        assertThat(eventSurveyAnswer.question, is("Please rate your skill level."));
        assertThat(eventSurveyAnswer.questionId, is(22134618L));
        assertThat(eventSurveyAnswer.answer, is("Expert"));
        assertThat(eventSurveyAnswer.updated, is(1420042915000L));
    }
}
