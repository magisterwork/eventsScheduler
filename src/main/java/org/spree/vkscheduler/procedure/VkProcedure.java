package org.spree.vkscheduler.procedure;

import com.google.gson.JsonElement;

import java.util.Map;

public interface VkProcedure<T extends VkProcedure.Query> {

    JsonElement execute(T query);

    interface Query {
        Map<String, Object> params();
    }
}
