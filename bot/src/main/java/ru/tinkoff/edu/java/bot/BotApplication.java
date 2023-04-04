package ru.tinkoff.edu.java.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.bot.ñlient.ScrapperClient;
import ru.tinkoff.edu.java.bot.ñlient.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.ñlient.request.RemoveLinkRequest;

import java.net.URI;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(BotApplication.class, args);
        ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
        System.out.println(config);

        var cl = ctx.getBean(ScrapperClient.class);
        System.out.println(cl.track(123L, new AddLinkRequest(URI.create(""))));
        System.out.println(cl.untrack(123L, new RemoveLinkRequest(URI.create(""))));
        System.out.println(cl.getAllLinks(123L));
    }
}
