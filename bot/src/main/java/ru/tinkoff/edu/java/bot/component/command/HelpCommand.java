package ru.tinkoff.edu.java.bot.component.command;

import com.pengrad.telegrambot.model.BotCommand;
import org.springframework.stereotype.Component;

@Component
public final class HelpCommand extends BotCommand {

    public HelpCommand() {
        super(
                "/help",
                "вывести окно с командами"
        );
    }
}
