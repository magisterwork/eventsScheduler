package org.spree.vkscheduler.eventsource.searchfactory;

import org.spree.vkscheduler.procedure.GetGroupsProcedure;
import org.spree.vkscheduler.procedure.VkProcedure;
import org.spree.vkscheduler.procedure.VkSearchFactory;

public class WithNumbersVkSearchFactory<T> implements VkSearchFactory {

    private final VkSearchFactory inner;

    private ThreadLocal<VkProcedure.Query> previousQuery;

    public WithNumbersVkSearchFactory(VkSearchFactory inner) {
        this.inner = inner;
    }

    @Override
    public VkProcedure.Query nextRequest() {
        return new GetGroupsProcedure.Query();
    }

}
