package org.spree.vkscheduler.procedure;

import com.google.gson.JsonElement;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.queries.execute.ExecuteStorageFunctionQuery;
import org.spree.vkscheduler.exception.VkProcedureException;

public class GetGroupsProcedure implements VkProcedure {

    public static final String STORAGE_FUNCTION_NAME = "getGroups";

    private final VkApiClient client;
    private final UserActor actor;

    public GetGroupsProcedure(VkApiClient client, UserActor actor) {
        this.client = client;
        this.actor = actor;
    }

    @Override
    public JsonElement execute(String text) {
        ExecuteStorageFunctionQuery query = client.execute().storageFunction(actor, STORAGE_FUNCTION_NAME);
        query.unsafeParam("text", text);;
        try {
            return query.execute();
        } catch (ApiException | ClientException e) {
            throw new VkProcedureException(e);
        }
    }
}
