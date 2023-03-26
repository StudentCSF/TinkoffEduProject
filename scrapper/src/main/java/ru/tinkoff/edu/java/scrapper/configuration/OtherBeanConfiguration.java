package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.scheduler.LinkUpdaterScheduler;

@Configuration
public class OtherBeanConfiguration {

    @Bean
    public long schedulerIntervalMillis(ApplicationConfig config) {
        return config.scheduler().interval().toMillis();
    }

    @Bean
    public LinkUpdaterScheduler linkUpdaterScheduler() {
        return new LinkUpdaterScheduler();
    }
}
