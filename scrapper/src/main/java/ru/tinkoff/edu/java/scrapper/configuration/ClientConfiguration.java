package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {

    @Bean
    public WebClient gitHubWebClient(
            @Value("${app.github-base-url:https://api.github.com}")
                    String baseUrl
    ) {
        return buildWebClient(
                baseUrl
        );
    }

    @Bean
    public WebClient stackOverflowWebClient(
            @Value("${app.stackoverflow-base-url:https://api.stackexchange.com/2.3}")
                    String baseUrl
    ) {
        return buildWebClient(
                baseUrl
        );
    }

    private WebClient buildWebClient(String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
