package ru.tinkoff.edu.java.scrapper.client.github;

import org.springframework.web.reactive.function.client.WebClient;

public final class GitHubClient
        implements IGitHubClient {

    private static final String DEFAULT_BASE_URL = "https://api.github.com";

    private final WebClient webClient;

    public GitHubClient() {
        this(DEFAULT_BASE_URL);
    }

    public GitHubClient(String baseUrl) {
        this.webClient =
                WebClient.builder()
                        .baseUrl(baseUrl)
                        .build();
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
