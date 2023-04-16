package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.tinkoff.edu.java.bot.component.handler.UnknownCommandHandler;

@SpringBootTest
class BotServiceTest {

    @Autowired
    private BotService botService;

    @MockBean
    private UnknownCommandHandler unknownCommandHandler;

    @Test
    void process() {
        Update upd = new DumbUpdate();
        this.botService.getMessage(new DumbUpdate());
        Mockito.verify(unknownCommandHandler).handle(upd);

    }

    static class DumbUpdate extends Update {
        static class DumbMessage extends Message {
            static class DumbChat extends Chat {
                @Override
                public Long id() {
                    return 1L;
                }
            }

            @Override
            public String text() {
                return "/dumb";
            }

            @Override
            public Chat chat() {
                return new DumbChat();
            }
        }

        @Override
        public Message message() {
            return new DumbMessage();
        }
    }
}