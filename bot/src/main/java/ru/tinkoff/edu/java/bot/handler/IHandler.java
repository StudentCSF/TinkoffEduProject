package ru.tinkoff.edu.java.bot.handler;

import com.pengrad.telegrambot.request.SendMessage;

public sealed interface IHandler
        permits StartHandlerImpl, HelpHandlerImpl,
        TrackHandlerImpl, UntrackHandlerImpl, ListHandlerImpl
{
    SendMessage handle();
}
