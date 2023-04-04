package ru.tinkoff.edu.java.scrapper.data.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.net.URI;

@Schema
public record LinkResponse(
        Long id,
        URI url
) {
}
