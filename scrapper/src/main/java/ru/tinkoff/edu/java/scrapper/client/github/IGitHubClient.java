package ru.tinkoff.edu.java.scrapper.client.github;

public sealed interface IGitHubClient
        permits GitHubClient {

    RepoResponse fetchRepo(String user, String repo);
}
