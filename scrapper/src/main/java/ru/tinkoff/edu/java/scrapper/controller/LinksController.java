package ru.tinkoff.edu.java.scrapper.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.data.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.data.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.data.response.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.data.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.data.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.service.DumbService;

import java.util.List;

@RestController
@RequestMapping("/links")
public record LinksController(
        DumbService dumbService
) {

    @GetMapping
    @Operation(summary = "Получить все отслеживаемые ссылки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ссылки успешно получены"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректные параметры запроса",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    public ListLinksResponse getAllLinks(
            @Valid @RequestHeader(name = "Tg-Chat-Id") Long id
    ) {
        List<LinkResponse> list = this.dumbService.getAll(id);
        return new ListLinksResponse(list, list.size());
    }

    @PostMapping
    @Operation(summary = "Добавить отслеживание ссылки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ссылка успешно добавлена"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректные параметры запроса",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    public LinkResponse addLinkTracking(
            @Valid @RequestHeader(name = "Tg-Chat-Id") Long id,
            @Valid @RequestBody AddLinkRequest addLinkRequest
    ) {
        return this.dumbService.add(id, addLinkRequest);
    }

    @DeleteMapping
    @Operation(summary = "Убрать отслеживание ссылки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ссылка успешно убрана"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректные параметры запроса",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ссылка не найдена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    })
    public LinkResponse removeLinkTracking(
            @Valid @RequestHeader(name = "Tg-Chat-Id") Long id,
            @Valid @RequestBody RemoveLinkRequest removeLinkRequest
    ) {
        return this.dumbService.remove(id, removeLinkRequest);
    }
}
