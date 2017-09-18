package org.spree.vkscheduler.procedure;

import com.google.gson.JsonElement;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.Actor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.queries.execute.ExecuteStorageFunctionQuery;
import org.spree.vkscheduler.exception.VkProcedureException;

import java.util.Map;

public class GetGroupsProcedure implements VkProcedure {

    public static final String STORAGE_FUNCTION_NAME = "getGroups";
    private final String text;
    private final String cityId;
    private final VkApiClient client;
    private final Actor actor;

    public GetGroupsProcedure(String text, String cityId, VkApiClient client, Actor actor) {
        this.text = text;
        this.cityId = cityId;
        this.client = client;
        this.actor = actor;
    }

    @Override
    public JsonElement execute() {
        ExecuteStorageFunctionQuery query = client.execute().storageFunction(actor, STORAGE_FUNCTION_NAME);
        query.unsafeParam("text", text);
        query.unsafeParam("cityId", cityId);
        try {
            return query.execute();
        } catch (ApiException | ClientException e) {
            throw new VkProcedureException(e);
        }
    }
}
