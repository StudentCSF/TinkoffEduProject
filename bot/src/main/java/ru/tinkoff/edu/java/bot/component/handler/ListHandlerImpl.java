package ru.tinkoff.edu.java.bot.component.handler;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.component.command.ListCommand;
import ru.tinkoff.edu.java.bot.exception.UnregisteredUserException;
import ru.tinkoff.edu.java.bot.client.IScrapperClient;
import ru.tinkoff.edu.java.bot.client.response.ListLinksResponse;

@Component
public record ListHandlerImpl(
        IScrapperClient scrapperClient,
        StringBuilder stringBuilder,
        ListCommand handledCommand
) implements IHandler<ListCommand> {

    private static final String BOT_REPLY_UNREGISTERED = "Я Вас почему-то не знаю. Пожалуйста, отправьте /start";

    @Override
    public SendMessage handle(Update update) {
        String command = update.message().text();
        Long id = update.message().chat().id();
        if (command.equals(handledCommand.command())) {
            String reply = determineReply(id);
            return new SendMessage(id, reply);
        }
        return null;
    }

    String determineReply(Long id) {
        try {
            ListLinksResponse resp = this.scrapperClient.getAllLinks(id);
            stringBuilder.delete(0, stringBuilder.length());
            if (resp.links().size() > 0) {
                resp.links().forEach(
                        x -> stringBuilder.append(x.url()).append('\n')
                );
                return stringBuilder.toString();
            } else {
                return "Вы пока ничего не отслеживаете";
            }
        } catch (UnregisteredUserException e) {
            return BOT_REPLY_UNREGISTERED;
        }
    }

    @Override
    public ListCommand handledCommand() {
        return this.handledCommand;
    }
}
