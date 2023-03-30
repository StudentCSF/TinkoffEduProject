package ru.tinkoff.edu.java.scrapper.data.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Schema
@Validated
public record AddLinkRequest(
        URI link
) {
}
