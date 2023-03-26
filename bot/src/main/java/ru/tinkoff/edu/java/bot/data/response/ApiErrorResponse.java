package ru.tinkoff.edu.java.bot.data.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record ApiErrorResponse(
        String description,
        String code,
        String exceptionName,
        String exceptionMessage,
        String[] stackTrace
) {
}
