package ru.tinkoff.edu.java.bot.ñlient;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.ñlient.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.ñlient.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.ñlient.response.LinkResponse;
import ru.tinkoff.edu.java.bot.ñlient.response.ListLinksResponse;

@Component
public record ScrapperClient(
        WebClient scrapperWebClient
) {

    public ListLinksResponse getAllLinks(Long chatId) {
        return this.scrapperWebClient
                .get()
                .uri("/links")
                .header("TG-Chat-Id", String.valueOf(chatId))
                .retrieve()
                .bodyToMono(ListLinksResponse.class)
                .block();
    }

    public LinkResponse track(Long chatId, AddLinkRequest request) {
        return this.scrapperWebClient
                .post()
                .uri("/links")
                .header("TG-Chat-Id", String.valueOf(chatId))
                .body(Mono.just(request), AddLinkRequest.class)
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }

    public LinkResponse untrack(Long chatId, RemoveLinkRequest request) {
        return this.scrapperWebClient
                .method(HttpMethod.DELETE)
                .uri("/links")
                .header("TG-Chat-Id", String.valueOf(chatId))
                .body(Mono.just(request), RemoveLinkRequest.class)
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }
}
