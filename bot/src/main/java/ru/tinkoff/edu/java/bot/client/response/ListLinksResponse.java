package ru.tinkoff.edu.java.bot.client.response;

import java.util.List;

public record ListLinksResponse(
        List<LinkResponse> links,
        Integer size
) {
}
