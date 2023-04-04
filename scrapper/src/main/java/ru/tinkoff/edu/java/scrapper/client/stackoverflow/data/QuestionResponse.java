package ru.tinkoff.edu.java.scrapper.client.stackoverflow.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record QuestionResponse(

        @JsonProperty(value = "is_answered")
        Boolean isAnswered,

        @JsonProperty(value = "last_edit_date")
        OffsetDateTime lastEditDate
) {
}
