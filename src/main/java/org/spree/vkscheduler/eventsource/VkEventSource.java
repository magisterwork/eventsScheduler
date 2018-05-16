package org.spree.vkscheduler.eventsource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.vk.api.sdk.objects.groups.GroupFull;
import org.spree.core.event.Event;
import org.spree.core.event.EventSource;
import org.spree.vkscheduler.event.VkEvent;
import org.spree.vkscheduler.procedure.GetGroupsProcedure;
import org.spree.vkscheduler.procedure.VkProcedure;
import org.spree.vkscheduler.procedure.VkSearchFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VkEventSource implements EventSource<Event> {

    public static final Type RESPONSE_TYPE = new TypeToken<ArrayList<GroupFull>>() {
    }.getType();

    private final VkProcedure<VkProcedure.Query> groupsProcedure;
    private final VkSearchFactory searchFactory;
    private Gson gson;

    public VkEventSource(VkProcedure<VkProcedure.Query> groupsProcedure, VkSearchFactory searchFactory, Gson gson) {
        this.groupsProcedure = groupsProcedure;
        this.searchFactory = searchFactory;
        this.gson = gson;
    }

    @Override
    public Iterable<Event> getNew() {
        VkProcedure.Query query = searchFactory.nextRequest();
        List<GroupFull> groups = getGroups(query);

        return groups.stream()
                .map(VkEvent::new)
                .collect(Collectors.toList());
    }

    private List<GroupFull> getGroups(VkProcedure.Query query) {
        JsonElement response = groupsProcedure.execute(query);
        return gson.fromJson(response, RESPONSE_TYPE);
    }
}
