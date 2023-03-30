package ru.tinkoff.edu.java.scrapper.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.client.github.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.github.IGitHubClient;
import ru.tinkoff.edu.java.scrapper.client.stackoverflow.IStackOverflowClient;
import ru.tinkoff.edu.java.scrapper.client.stackoverflow.StackOverflowClient;

@Configuration
public class ClientConfiguration {

    @Bean
    public IGitHubClient gitHubClient() {
        return new GitHubClient();
    }

    @Bean
    public IStackOverflowClient stackOverflowClient() {
        return new StackOverflowClient();
    }
}
