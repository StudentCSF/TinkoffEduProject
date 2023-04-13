package ru.tinkoff.edu.java.bot.component.handler;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.component.command.HelpCommand;

import java.util.List;

@Component
public record HelpHandlerImpl(
        List<IHandler<? extends BotCommand>> handlers,
        StringBuilder stringBuilder,
        HelpCommand handledCommand
) implements IHandler<HelpCommand> {

    private static final String DIVIDER = " - ";

    @Override
    public SendMessage handle(Update update) {
        Long id = update.message().chat().id();
        String command = update.message().text();
        if (command.equals(handledCommand.command())) {
            this.stringBuilder.delete(0, this.stringBuilder.length());
            handlers.forEach(
                    x -> stringBuilder.append(x.handledCommand().command())
                            .append(DIVIDER)
                            .append(x.handledCommand().description())
                            .append('\n'));
            stringBuilder.append(this.handledCommand().command())
                    .append(DIVIDER)
                    .append(this.handledCommand().description());
            return new SendMessage(id, stringBuilder.toString());
        }
        return null;
    }

    @Override
    public HelpCommand handledCommand() {
        return this.handledCommand;
    }
}
