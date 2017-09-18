package org.spree.vkscheduler.authentication;

import com.vk.api.sdk.client.actors.Actor;

public interface VkAuthentication<T extends Actor> {

    T getActor();
}
