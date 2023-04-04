package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.�lient.ScrapperClient;

@Service
public record BotService(
        TelegramBot telegramBot,
        ScrapperClient scrapperClient
) {
}
