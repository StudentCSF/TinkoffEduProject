package ru.tinkoff.edu.java.scrapper.client.github;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public record GitHubClient(
        WebClient gitHubWebClient
) implements IGitHubClient {

    @Override
    public RepoResponse fetchRepo(String user, String repo) {
        return this.gitHubWebClient.get()
                .uri(String.format("/repos/%s/%s", user, repo))
                .retrieve()
                .bodyToMono(RepoResponse.class)
                .block();
    }
}
