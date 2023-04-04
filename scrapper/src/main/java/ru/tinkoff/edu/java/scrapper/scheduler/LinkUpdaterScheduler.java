package ru.tinkoff.edu.java.scrapper.scheduler;

import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Log
public record LinkUpdaterScheduler() {

    @Scheduled(fixedDelayString = "#{@schedulerIntervalMillis}")
    public void update() {
        log.info("update");
    }
}
