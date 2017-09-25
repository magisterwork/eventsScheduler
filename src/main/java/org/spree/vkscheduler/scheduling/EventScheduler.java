package org.spree.vkscheduler.scheduling;

import org.spree.core.event.Event;
import org.spree.core.event.EventSource;
import org.spree.core.event.JdbcEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;

public class EventScheduler {

    private EventSource<Event> source;
    private JdbcTemplate jdbc;

    public EventScheduler(EventSource<Event> source, JdbcTemplate jdbc) {
        this.source = source;
        this.jdbc = jdbc;
    }

    @Scheduled(fixedDelay = 30000)
    public void load() {
        for (Event event : source.getNew()) {
            new JdbcEvent(jdbc, event).save();
        }
    }
}
