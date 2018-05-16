package org.spree.vkscheduler.eventsource.searchfactory;

public interface SearchFactory<T> {

    T nextRequest();
}
