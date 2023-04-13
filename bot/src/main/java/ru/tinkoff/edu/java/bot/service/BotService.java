package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.component.handler.IHandler;
import ru.tinkoff.edu.java.bot.component.handler.UnknownCommandHandler;

import java.util.List;

@Service
public final class BotService
        implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final List<IHandler<? extends BotCommand>> handlers;
    private final UnknownCommandHandler unknownCommandHandler;

    BotService(TelegramBot telegramBot,
               List<IHandler<? extends BotCommand>> handlers,
               UnknownCommandHandler unknownCommandHandler
    ) {
        this.telegramBot = telegramBot;
        this.handlers = handlers;
        this.unknownCommandHandler = unknownCommandHandler;
        this.telegramBot.setUpdatesListener(this);
    }

    <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(BaseRequest<T, R> request) {
        this.telegramBot.execute(request);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            SendMessage resp = getMessage(update);
            this.execute(resp);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    SendMessage getMessage(Update update) {
        SendMessage result = null;
        for (IHandler<? extends BotCommand> handler : this.handlers) {
            result = handler.handle(update);
            if (result != null) break;
        }
        if (result == null) {
            result = unknownCommandHandler.handle(update);
        }
        return result;
    }
}
