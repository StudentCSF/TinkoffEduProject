package ru.tinkoff.edu.java.bot.ñlient;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.exception.UnregisteredUserException;
import ru.tinkoff.edu.java.bot.ñlient.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.ñlient.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.ñlient.response.LinkResponse;
import ru.tinkoff.edu.java.bot.ñlient.response.ListLinksResponse;

@Component
public record ScrapperClient(
        WebClient scrapperWebClient
) implements IScrapperClient {

    private static final String LINKS_ENDPOINT = "links";
    private static final String TG_CHAT_ENDPOINT = "tg-chat";
    private static final String TG_CHAT_ID_HEADER = "TG-Chat-Id";

    @Override
    public boolean register(Long id) {
        var response = this.scrapperWebClient
                .post()
                .uri(String.format("%s/%s", TG_CHAT_ENDPOINT, id))
                .retrieve()
                .toBodilessEntity()
                .block();
        if (response != null)
            System.out.println(response.getStatusCode());
        return response != null && response.getStatusCode() == HttpStatusCode.valueOf(200);
    }

    @Override
    public void unregister(Long id) {
        this.scrapperWebClient
                .delete()
                .uri(String.format("%s/%s", TG_CHAT_ENDPOINT, id))
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    @Override
    public ListLinksResponse getAllLinks(Long chatId) {
        return this.scrapperWebClient
                .get()
                .uri(LINKS_ENDPOINT)
                .header(TG_CHAT_ID_HEADER, String.valueOf(chatId))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        err -> Mono.error(UnregisteredUserException::new))
                .bodyToMono(ListLinksResponse.class)
                .block();
    }

    @Override
    public LinkResponse track(Long chatId, AddLinkRequest request) {
        return this.scrapperWebClient
                .post()
                .uri(LINKS_ENDPOINT)
                .header(TG_CHAT_ID_HEADER, String.valueOf(chatId))
                .body(Mono.just(request), AddLinkRequest.class)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        err -> Mono.error(UnregisteredUserException::new))
                .bodyToMono(LinkResponse.class)
                .block();
    }

    @Override
    public LinkResponse untrack(Long chatId, RemoveLinkRequest request) {
        return this.scrapperWebClient
                .method(HttpMethod.DELETE)
                .uri(LINKS_ENDPOINT)
                .header(TG_CHAT_ID_HEADER, String.valueOf(chatId))
                .body(Mono.just(request), RemoveLinkRequest.class)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        err -> Mono.error(UnregisteredUserException::new))
                .bodyToMono(LinkResponse.class)
                .block();
    }
}
