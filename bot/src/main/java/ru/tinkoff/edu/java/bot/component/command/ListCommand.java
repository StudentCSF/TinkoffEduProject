package ru.tinkoff.edu.java.bot.component.command;

import com.pengrad.telegrambot.model.BotCommand;
import org.springframework.stereotype.Component;

@Component
public class ListCommand extends BotCommand {

    public ListCommand() {
        super(
                "/list",
                "показать список отслеживаемых ссылок"
        );
    }
}
