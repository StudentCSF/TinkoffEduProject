package ru.tinkoff.edu.java.bot.component.handler;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class UnknownCommandHandler {

    public SendMessage handle(Update update) {
        Long id = update.message().chat().id();
        return new SendMessage(id,
                "Я не знаю такой команды :(\nОтправьте /help для вывода списка известных мне команд");
    }
}
