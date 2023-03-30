package ru.tinkoff.edu.java.bot.data.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import java.util.List;

@Schema
@Validated
public record LinkUpdate(
        Long id,
        URI url,
        String description,
        List<Long> tgChatIds
) {

}
