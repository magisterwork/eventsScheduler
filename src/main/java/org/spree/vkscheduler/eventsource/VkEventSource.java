package org.spree.vkscheduler.eventsource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.vk.api.sdk.objects.groups.GroupFull;
import org.spree.core.event.Event;
import org.spree.core.event.EventSource;
import org.spree.vkscheduler.event.VkEvent;
import org.spree.vkscheduler.eventsource.searchfactory.SearchFactory;
import org.spree.vkscheduler.procedure.VkProcedure;
import org.springframework.scheduling.annotation.Scheduled;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VkEventSource implements EventSource<Event> {

    public static final Type RESPONSE_TYPE = new TypeToken<ArrayList<GroupFull>>() {}.getType();

    private final VkProcedure groupsProcedure;
    private final SearchFactory searchFactory;
    private Gson gson;

    public VkEventSource(VkProcedure groupsProcedure, SearchFactory searchFactory, Gson gson) {
        this.groupsProcedure = groupsProcedure;
        this.searchFactory = searchFactory;
        this.gson = gson;
    }

    @Override
    public Collection<Event> getNew() {
        JsonElement response = groupsProcedure.execute(searchFactory.nextText());
        List<GroupFull> groups = gson.fromJson(response, RESPONSE_TYPE);
        return groups.stream()
                .map(VkEvent::new)
                .collect(Collectors.toList());
    }
}
