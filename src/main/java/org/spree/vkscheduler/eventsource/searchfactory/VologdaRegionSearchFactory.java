package org.spree.vkscheduler.eventsource.searchfactory;

import org.spree.vkscheduler.procedure.GetGroupsProcedure;
import org.spree.vkscheduler.procedure.VkProcedure;
import org.spree.vkscheduler.procedure.VkSearchFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class VologdaRegionSearchFactory implements VkSearchFactory {

    private final List<VkCity> cities = Arrays.asList(
            new VkCity(41, "Вологда"),
            new VkCity(8, "Череповец"),
            new VkCity(5667, "Кириллов")
    );
    private Iterator<VkCity> iterator = cities.iterator();
    private int count = 50;

    @Override
    public VkProcedure.Query nextRequest() {
        if (!iterator.hasNext()) {
            iterator = cities.iterator();
        }
        VkCity city = iterator.next();
        return new GetGroupsProcedure.Query(city.name, city.id);
    }

    private class VkCity {
        VkCity(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int id;
        String name;
    }
}
