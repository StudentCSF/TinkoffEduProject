package ru.tinkoff.edu.java.bot.ñlient.response;

import ru.tinkoff.edu.java.bot.ñlient.response.LinkResponse;

import java.util.List;

public record ListLinksResponse(
        List<LinkResponse> links,
        Integer size
) {
}
