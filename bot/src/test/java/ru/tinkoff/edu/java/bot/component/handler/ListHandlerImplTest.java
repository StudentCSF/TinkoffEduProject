package ru.tinkoff.edu.java.bot.component.handler;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.tinkoff.edu.java.bot.component.command.ListCommand;
import ru.tinkoff.edu.java.bot.configuration.CommonBeansConfiguration;
import ru.tinkoff.edu.java.bot.client.IScrapperClient;
import ru.tinkoff.edu.java.bot.client.response.LinkResponse;
import ru.tinkoff.edu.java.bot.client.response.ListLinksResponse;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {ListHandlerImpl.class, ListCommand.class, CommonBeansConfiguration.class})
class ListHandlerImplTest {

    @Autowired
    private ListHandlerImpl handler;

    @MockBean
    private IScrapperClient scrapperClient;

    @Test
    void handle() {
        Long id = 1L;
        Mockito.when(scrapperClient.getAllLinks(id))
                .thenReturn(new ListLinksResponse(
                        new ArrayList<>(
                                List.of(
                                        new LinkResponse(id, URI.create("a")))),
                        1
                ));
        assertEquals(handler.determineReply(id),
                "a\n");
    }

    @Test
    void handle2() {
        Long id = 1L;
        Mockito.when(scrapperClient.getAllLinks(id))
                .thenReturn(
                        new ListLinksResponse(new ArrayList<>(), 0)
                );
        assertEquals(handler.determineReply(id),
                "Вы пока ничего не отслеживаете");
    }
}