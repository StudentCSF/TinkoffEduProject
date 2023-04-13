package ru.tinkoff.edu.java.bot.ñlient;

import ru.tinkoff.edu.java.bot.ñlient.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.ñlient.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.ñlient.response.LinkResponse;
import ru.tinkoff.edu.java.bot.ñlient.response.ListLinksResponse;

public interface IScrapperClient {

    boolean register(Long id);

    void unregister(Long id);

    ListLinksResponse getAllLinks(Long chatId);

    LinkResponse track(Long chatId, AddLinkRequest request);

    LinkResponse untrack(Long chatId, RemoveLinkRequest request);
}
