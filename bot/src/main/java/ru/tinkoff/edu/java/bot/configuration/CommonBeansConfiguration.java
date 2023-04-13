package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBeansConfiguration {

    @Bean
    public StringBuilder stringBuilder() {
        return new StringBuilder();
    }
}
