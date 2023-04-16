package ru.tinkoff.edu.java.bot.component.handler;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.component.command.StartCommand;
import ru.tinkoff.edu.java.bot.�lient.IScrapperClient;

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
                return new SendMessage(id, "������!\n� ��� �� ������������ ����������.");
            } else {
                return new SendMessage(id, "���, ���-�� �� ���...");
            }
        }
        return null;
    }

    @Override
    public StartCommand handledCommand() {
        return this.handledCommand;
    }
}
