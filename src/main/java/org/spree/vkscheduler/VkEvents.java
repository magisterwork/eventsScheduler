package org.spree.vkscheduler;

import org.spree.core.external.ExternalEventSource;

import java.util.Collection;

public class VkEvents implements ExternalEventSource<VkEvent>{

    private VkApiCLient apiClient;


    @Override
    public Collection<VkEvent> getNew() {
        return null;
    }
}
