package org.spree.vkscheduler.event;

import org.spree.core.event.Event;
import org.spree.core.event.EventSource;

import java.util.Collection;

public class VkEventSource implements EventSource<Event> {

    @Override
    public Collection<Event> getNew() {
        return null;
    }
}
