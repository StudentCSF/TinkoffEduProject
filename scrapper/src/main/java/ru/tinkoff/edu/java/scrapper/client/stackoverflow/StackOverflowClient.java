package ru.tinkoff.edu.java.scrapper.client.stackoverflow;

import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.client.stackoverflow.data.PreQuestionResponse;
import ru.tinkoff.edu.java.scrapper.client.stackoverflow.data.QuestionResponse;

public record StackOverflowClient(
        WebClient webClient
) implements IStackOverflowClient {

    private static final String DEFAULT_BASE_URL = "https://api.stackexchange.com/2.3";

    public static StackOverflowClient create() {
        return create(DEFAULT_BASE_URL);
    }

    public static StackOverflowClient create(String baseUrl) {
        return new StackOverflowClient(
                WebClient.builder()
                        .baseUrl(baseUrl)
                        .build()
        );
    }

    @Override
    public QuestionResponse fetchQuestion(Long id) {
        PreQuestionResponse preQuestionResponse = this.webClient.get()
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
