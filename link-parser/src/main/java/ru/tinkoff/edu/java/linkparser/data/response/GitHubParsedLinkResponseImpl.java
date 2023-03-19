package ru.tinkoff.edu.java.linkparser.data.response;

public record GitHubParsedLinkResponseImpl(
        String username,
        String repositoryName
) implements IParsedLinkResponse { }
