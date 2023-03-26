package ru.tinkoff.edu.java.scrapper.client.github;

import org.springframework.web.reactive.function.client.WebClient;

public record GitHubClient(
        WebClient webClient
) implements IGitHubClient {

    private static final String DEFAULT_BASE_URL = "https://api.github.com";

    public static GitHubClient create() {
        return create(DEFAULT_BASE_URL);
    }

    public static GitHubClient create(String baseUrl) {
        return new GitHubClient(
                WebClient.builder()
                        .baseUrl(baseUrl)
                        .build()
        );
    }

    @Override
    public RepoResponse fetchRepo(String user, String repo) {
        return this.webClient.get()
                .uri(String.format("/repos/%s/%s", user, repo))
                .retrieve()
                .bodyToMono(RepoResponse.class)
                .block();
    }
}
