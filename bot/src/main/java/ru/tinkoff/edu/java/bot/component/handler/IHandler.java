package ru.tinkoff.edu.java.bot.component.handler;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public sealed interface IHandler<T extends BotCommand>
        permits StartHandlerImpl, HelpHandlerImpl,
        TrackHandlerImpl, UntrackHandlerImpl, ListHandlerImpl {

    SendMessage handle(Update update);

    T handledCommand();
}
