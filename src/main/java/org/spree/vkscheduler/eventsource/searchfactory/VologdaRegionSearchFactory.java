package org.spree.vkscheduler.eventsource.searchfactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class VologdaRegionSearchFactory implements SearchFactory {

    private static final List<String> texts = Arrays.asList("Вологда", "Череповец", "Кириллов", "Шексна", "Сокол", "Тотьма");
    private static Iterator<String> iter = texts.iterator();

    @Override
    public String nextText() {
        if (!iter.hasNext()) {
            iter = texts.iterator();
        }
        return iter.next();
    }
}
