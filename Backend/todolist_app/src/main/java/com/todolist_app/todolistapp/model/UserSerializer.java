package com.todolist_app.todolistapp.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {
    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", user.getId().toString());
        jsonGenerator.writeStringField("first_name", user.getFirst_name());
        jsonGenerator.writeStringField("last_name", user.getLast_name());
        jsonGenerator.writeStringField("email", user.getEmail());
        jsonGenerator.writeStringField("password", user.getPassword());

        jsonGenerator.writeFieldName("tasks");
        jsonGenerator.writeStartArray();

        for (Task task : user.getTasks()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", task.getId().toString());
            jsonGenerator.writeStringField("task", task.getTask());
            jsonGenerator.writeStringField("user_id", task.getId().toString());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}

