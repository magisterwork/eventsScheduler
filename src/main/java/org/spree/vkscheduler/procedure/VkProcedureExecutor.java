package org.spree.vkscheduler.procedure;

import com.google.gson.JsonElement;
import com.vk.api.sdk.queries.execute.ExecuteStorageFunctionQuery;

public interface VkProcedureExecutor {

    JsonElement execute(ExecuteStorageFunctionQuery query);
}
