package ru.tinkoff.edu.java.bot.client.response;

import java.net.URI;

public record LinkResponse(
        Long id,
        URI url
) {
}
