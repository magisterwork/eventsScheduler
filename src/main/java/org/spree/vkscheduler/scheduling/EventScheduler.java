package org.spree.vkscheduler.scheduling;

import org.spree.core.entities.JpaEvent;
import org.spree.core.event.CatchedStoredEvent;
import org.spree.core.event.Event;
import org.spree.core.event.EventSource;
import org.spree.core.event.JpaStoredEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Scheduled;

public class EventScheduler {

    private CrudRepository<JpaEvent, JpaEvent.EventId> repository;
    private EventSource<Event> source;

    public EventScheduler(CrudRepository<JpaEvent, JpaEvent.EventId> repository, EventSource<Event> source) {
        this.repository = repository;
        this.source = source;
    }

    @Scheduled(fixedDelay = 10000)
    public void load() {
        for (Event event : source.getNew()) {
            new CatchedStoredEvent(new JpaStoredEvent(new JpaEvent(event), repository)).save();
        }
    }
}
