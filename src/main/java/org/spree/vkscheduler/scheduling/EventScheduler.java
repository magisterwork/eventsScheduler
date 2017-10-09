package org.spree.vkscheduler.scheduling;

import org.spree.core.entities.JpaEvent;
import org.spree.core.event.Event;
import org.spree.core.event.EventSource;
import org.spree.core.event.JdbcEvent;
import org.spree.core.event.JpaStoredEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;

public class EventScheduler {

    private EventSource<Event> source;

    public EventScheduler(EventSource<Event> source) {
        this.source = source;
    }

    @Scheduled(fixedDelay = 60000)
    public void load() {
        for (Event event : source.getNew()) {
            new JpaStoredEvent(new JpaEvent(event)).save();
        }
    }
}
