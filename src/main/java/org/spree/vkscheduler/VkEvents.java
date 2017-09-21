package org.spree.vkscheduler;

import org.spree.core.external.ExternalEventSource;

import java.util.Collection;

public class VkEvents implements ExternalEventSource<VkStoredEvent>{

    private VkApiCLient apiClient;


    @Override
    public Collection<VkStoredEvent> getNew() {
        return null;
    }
}
