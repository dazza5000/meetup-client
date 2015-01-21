package com.bitgrind.meetup.api.gson;


import com.bitgrind.meetup.api.model.EventSurveyAnswer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;

public class EventSurveyAnswerDeserializer implements JsonDeserializer<EventSurveyAnswer> {

    @Override
    public EventSurveyAnswer deserialize(JsonElement json, Type typeOfT,
                                        JsonDeserializationContext context) {
        EventSurveyAnswer answer = null;
        if (json.isJsonObject()) {
            answer = new EventSurveyAnswer();
            JsonObject obj = json.getAsJsonObject();
            answer.questionId = obj.getAsJsonPrimitive("question_id").getAsLong();
            if (obj.has("answer")) {
                answer.answer = obj.getAsJsonPrimitive("answer").getAsString();
            }
            answer.question = obj.getAsJsonPrimitive("question").getAsString();
            if (obj.has("updated")) {
                answer.updated = obj.getAsJsonPrimitive("updated").getAsLong();
            }
        } else if (json.isJsonPrimitive()) {
            answer = new EventSurveyAnswer();
            JsonPrimitive primitive = json.getAsJsonPrimitive();
            if (primitive.isString()) {
                answer.answer = primitive.getAsString();
            }
        }
        return answer;
    }
}
