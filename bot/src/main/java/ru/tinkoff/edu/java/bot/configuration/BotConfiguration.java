package ru.tinkoff.edu.java.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfiguration {

    @Bean
    public TelegramBot telegramBot(
            ApplicationConfig config
    ) {
        TelegramBot tgbot = new TelegramBot(config.token());
        return tgbot;
    }
}