package ru.tinkoff.edu.java.bot.client.request;

import java.net.URI;

public record RemoveLinkRequest(
        URI link
) {
}
