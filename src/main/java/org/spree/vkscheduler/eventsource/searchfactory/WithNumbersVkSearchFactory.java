package org.spree.vkscheduler.eventsource.searchfactory;

import org.spree.vkscheduler.procedure.GetGroupsProcedure;
import org.spree.vkscheduler.procedure.VkProcedure;
import org.spree.vkscheduler.procedure.VkSearchFactory;

import java.util.*;

import static java.util.Collections.addAll;

public class WithNumbersVkSearchFactory implements VkSearchFactory {

    private final static Set<String> VALUES = new HashSet<>();
    {
        addAll(VALUES, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
                "24", "25", "26", "27", "28", "29", "30", "31", "декабрь", "январь", "февраль",
                "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь",
                "ноярь", "декабрь", Integer.toString(new GregorianCalendar().get(Calendar.YEAR)));
    }

    private final SearchFactory<VologdaRegionSearchFactory.VkCity> inner;

    private ThreadLocal<VologdaRegionSearchFactory.VkCity> city = new InheritableThreadLocal<VologdaRegionSearchFactory.VkCity>();
    private ThreadLocal<Iterator<String>> iterator = new InheritableThreadLocal<>();

    public WithNumbersVkSearchFactory(SearchFactory<VologdaRegionSearchFactory.VkCity> inner) {
        this.inner = inner;
    }

    @Override
    public VkProcedure.Query nextRequest() {
        if (city.get() == null || !iterator.get().hasNext()) {
            reinit();
            return new GetGroupsProcedure.Query(city.get().name, city.get().id);
        } else {
            return new GetGroupsProcedure.Query(iterator.get().next(), city.get().id);
        }
    }

    private void reinit() {
        city.set(inner.nextRequest());
        iterator.set(VALUES.iterator());
    }

}
