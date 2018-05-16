package org.spree.vkscheduler.procedure;

import com.google.gson.JsonElement;

import java.util.logging.Logger;

public class LoggedProcedure implements VkProcedure {

    private static final Logger LOG = Logger.getLogger(GetGroupsProcedure.class.getCanonicalName());
    private VkProcedure procedure;


    public LoggedProcedure(VkProcedure procedure) {
        this.procedure = procedure;
    }

    @Override
    public JsonElement execute(Query query) {
        LOG.fine("executing " + procedure.getClass() + "with query " + query);
        JsonElement result = procedure.execute(query);
        LOG.fine("result" + result);
        return result;
    }
}
