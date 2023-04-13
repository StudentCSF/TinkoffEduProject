package ru.tinkoff.edu.java.bot.component.handler;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.component.command.StartCommand;
import ru.tinkoff.edu.java.bot.сlient.IScrapperClient;

@Component
public record StartHandlerImpl(
        IScrapperClient scrapperClient,
        StartCommand handledCommand
) implements IHandler<StartCommand> {

    @Override
    public SendMessage handle(Update update) {
        String command = update.message().text();
        Long id = update.message().chat().id();
        if (command.equals(handledCommand.command())) {
            boolean isOk = this.scrapperClient.register(id);
            if (isOk) {
                return new SendMessage(id, "Привет!\nЯ бот по отслеживанию обновлений.");
            } else {
                return new SendMessage(id, "Упс, что-то не так...");
            }
        }
        return null;
    }

    @Override
    public StartCommand handledCommand() {
        return this.handledCommand;
    }
}
