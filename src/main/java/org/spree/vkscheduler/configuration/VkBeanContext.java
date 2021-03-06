package org.spree.vkscheduler.configuration;

import com.google.gson.Gson;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.spree.core.entities.JpaEvent;
import org.spree.core.event.Event;
import org.spree.core.event.EventSource;
import org.spree.core.event.NotEmptyDescriptionEventSource;
import org.spree.core.parameter.ConfigStorage;
import org.spree.vkscheduler.authentication.VkUserActorAuthentication;
import org.spree.vkscheduler.eventsource.VkEventSource;
import org.spree.vkscheduler.eventsource.searchfactory.VologdaRegionSearchFactory;
import org.spree.vkscheduler.eventsource.searchfactory.WithNumbersVkSearchFactory;
import org.spree.vkscheduler.procedure.GetGroupsProcedure;
import org.spree.vkscheduler.procedure.LoggedProcedure;
import org.spree.vkscheduler.procedure.VkProcedure;
import org.spree.vkscheduler.procedure.VkSearchFactory;
import org.spree.vkscheduler.scheduling.EventScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

@Configuration
public class VkBeanContext {

    @Autowired
    private ConfigStorage configStorage;
    @Autowired
    private CrudRepository<JpaEvent, JpaEvent.EventId> jpaEventCrudRepository;

    @Bean
    public EventScheduler eventScheduler() {
        return new EventScheduler(jpaEventCrudRepository, vkEventSource());
    }

    @Bean
    public VkApiClient vkApiClient() {
        return new VkApiClient(transportClient());
    }

    @Bean
    public VkUserActorAuthentication vkAuthentication() {
        return new VkUserActorAuthentication(configStorage, vkApiClient());
    }

    @Bean
    public EventSource vkEventSource() {
        return new NotEmptyDescriptionEventSource<>(
                new VkEventSource(groupProcedure(), searchFactory(), gson())
        );
    }

    @Bean
    @Qualifier("groupProcedure")
    public VkProcedure groupProcedure() {
        return new LoggedProcedure(
                new GetGroupsProcedure(vkApiClient(), vkAuthentication()
                )
        );
    }

    @Bean
    public VkSearchFactory searchFactory() {
        return new WithNumbersVkSearchFactory(new VologdaRegionSearchFactory());
    }

    @Bean
    public TransportClient transportClient() {
        return new HttpTransportClient();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
