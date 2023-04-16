package ru.tinkoff.edu.java.bot.�lient;

import ru.tinkoff.edu.java.bot.�lient.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.�lient.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.�lient.response.LinkResponse;
import ru.tinkoff.edu.java.bot.�lient.response.ListLinksResponse;

public interface IScrapperClient {

    boolean register(Long id);

    void unregister(Long id);

    ListLinksResponse getAllLinks(Long chatId);

    LinkResponse track(Long chatId, AddLinkRequest request);

    LinkResponse untrack(Long chatId, RemoveLinkRequest request);
}
