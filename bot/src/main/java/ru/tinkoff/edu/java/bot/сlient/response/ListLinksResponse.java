package ru.tinkoff.edu.java.bot.�lient.response;

import ru.tinkoff.edu.java.bot.�lient.response.LinkResponse;

import java.util.List;

public record ListLinksResponse(
        List<LinkResponse> links,
        Integer size
) {
}
