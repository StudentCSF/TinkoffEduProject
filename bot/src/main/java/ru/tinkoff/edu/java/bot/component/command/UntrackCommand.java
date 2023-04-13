package ru.tinkoff.edu.java.bot.component.command;

import com.pengrad.telegrambot.model.BotCommand;
import org.springframework.stereotype.Component;

@Component
public class UntrackCommand extends BotCommand {

    public UntrackCommand() {
        super(
                "/untrack",
                "прекратить отслеживание ссылки"
        );
    }
}
