package ru.tinkoff.edu.java.scrapper.client.stackoverflow.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public final class PreQuestionResponse {

    @JsonProperty(value = "items")
    private List<QuestionResponse> primitiveItems;
}
