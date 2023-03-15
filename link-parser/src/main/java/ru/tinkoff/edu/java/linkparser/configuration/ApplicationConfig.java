package ru.tinkoff.edu.java.linkparser.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.linkparser.component.GitHubLinkParserComponentImpl;
import ru.tinkoff.edu.java.linkparser.component.BaseLinkParserComponent;
import ru.tinkoff.edu.java.linkparser.component.StackOverflowLinkParserComponentImpl;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationConfig {

    @Bean
    public Map<String, Class<? extends BaseLinkParserComponent>> supportedLinks() {
        Map<String, Class<? extends BaseLinkParserComponent>> linksPrefixParserMap = new HashMap<>();
        linksPrefixParserMap.put("https://github.com/", GitHubLinkParserComponentImpl.class);
        linksPrefixParserMap.put("https://stackoverflow.com/", StackOverflowLinkParserComponentImpl.class);
        return linksPrefixParserMap;
    }
}
