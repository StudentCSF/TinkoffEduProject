package ru.tinkoff.edu.java.bot.component.command;

import com.pengrad.telegrambot.model.BotCommand;
import org.springframework.stereotype.Component;

@Component
public class StartCommand extends BotCommand {

    public StartCommand() {
        super(
                "/start",
                "зарегистрировать пользователя"
        );
    }
}
