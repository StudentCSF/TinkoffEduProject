package ru.tinkoff.edu.java.linkparser.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.linkparser.component.GitHubLinkParserComponentImpl;
import ru.tinkoff.edu.java.linkparser.component.ILinkParserComponent;
import ru.tinkoff.edu.java.linkparser.component.StackOverflowLinkParserComponentImpl;

import java.util.List;

@Configuration
public class ApplicationConfig {

    @Bean
    public List<ILinkParserComponent> parsers() {
        return List.of(
                new GitHubLinkParserComponentImpl(),
                new StackOverflowLinkParserComponentImpl()
        );
    }
}
