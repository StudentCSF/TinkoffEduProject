package ru.tinkoff.edu.java.scrapper.client.stackoverflow;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.client.stackoverflow.data.PreQuestionResponse;
import ru.tinkoff.edu.java.scrapper.client.stackoverflow.data.QuestionResponse;

@Component
public record StackOverflowClient(
        WebClient stackOverflowWebClient
) implements IStackOverflowClient {

    @Override
    public QuestionResponse fetchQuestion(Long id) {
        PreQuestionResponse preQuestionResponse = this.stackOverflowWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(String.format("/questions/%d", id))
                        .queryParam("site", "stackoverflow")
                        .build())
                .retrieve()
                .bodyToMono(PreQuestionResponse.class)
                .block();

        return preQuestionResponse == null
                || preQuestionResponse.getPrimitiveItems().size() < 1
                ? null
                : preQuestionResponse.getPrimitiveItems().get(0);
    }
}
