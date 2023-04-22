package ru.tinkoff.edu.java.bot.client;

import ru.tinkoff.edu.java.bot.client.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.client.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.client.response.LinkResponse;
import ru.tinkoff.edu.java.bot.client.response.ListLinksResponse;

public interface IScrapperClient {

    boolean register(Long id);

    void unregister(Long id);

    ListLinksResponse getAllLinks(Long chatId);

    LinkResponse track(Long chatId, AddLinkRequest request);

    LinkResponse untrack(Long chatId, RemoveLinkRequest request);
}
