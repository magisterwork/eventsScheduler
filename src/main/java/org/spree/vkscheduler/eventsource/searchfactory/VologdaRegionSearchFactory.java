package org.spree.vkscheduler.eventsource.searchfactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class VologdaRegionSearchFactory implements SearchFactory<VologdaRegionSearchFactory.VkCity> {

    private final List<VkCity> cities = Arrays.asList(
            new VkCity(41, "Вологда"),
            new VkCity(8, "Череповец"),
            new VkCity(5667, "Кириллов")
    );
    private Iterator<VkCity> iterator = cities.iterator();
    private int count = 50;

    @Override
    public VkCity nextRequest() {
        if (!iterator.hasNext()) {
            iterator = cities.iterator();
        }
        return iterator.next();
    }

    public class VkCity {
        VkCity(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int id;
        String name;
    }
}
