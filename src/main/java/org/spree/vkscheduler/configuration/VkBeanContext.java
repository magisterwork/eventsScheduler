package org.spree.vkscheduler.configuration;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.spree.core.parameter.ConfigStorage;
import org.spree.vkscheduler.authentication.VkAuthentication;
import org.spree.vkscheduler.authentication.VkUserActorAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VkBeanContext {

    @Autowired
    private ConfigStorage configStorage;

    @Bean
    public VkApiClient vkApiClient() {
        return new VkApiClient(transportClient());
    }

    @Bean
    public VkUserActorAuthentication vkAuthentication() {
        return new VkUserActorAuthentication(configStorage, vkApiClient());
    }

    @Bean
    public TransportClient transportClient() {
        return new HttpTransportClient();
    }
}
