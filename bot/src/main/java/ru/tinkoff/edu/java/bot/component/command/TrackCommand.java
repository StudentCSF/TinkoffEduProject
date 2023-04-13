package ru.tinkoff.edu.java.bot.component.command;

import com.pengrad.telegrambot.model.BotCommand;
import org.springframework.stereotype.Component;

@Component
public class TrackCommand extends BotCommand {

    public TrackCommand() {
        super(
                "/track",
                "начать отслеживание ссылки"
        );
    }
}
