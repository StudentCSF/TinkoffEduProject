package ru.tinkoff.edu.java.scrapper.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.tinkoff.edu.java.scrapper.ScrapperApplication;

@EnableScheduling
public record LinkUpdaterScheduler() {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScrapperApplication.class);

    @Scheduled(fixedDelayString = "#{@schedulerIntervalMillis}")
    public void update() {
        LOGGER.info("update");
    }
}
