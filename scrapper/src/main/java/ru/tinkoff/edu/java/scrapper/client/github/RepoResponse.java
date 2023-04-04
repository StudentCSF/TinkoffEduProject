package ru.tinkoff.edu.java.scrapper.client.github;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record RepoResponse(
        @JsonProperty("updated_at")
        OffsetDateTime updatedAt
) {
}
