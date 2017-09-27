package org.spree.vkscheduler.procedure;

import com.google.gson.JsonElement;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.queries.execute.ExecuteStorageFunctionQuery;
import org.spree.vkscheduler.authentication.VkUserActorAuthentication;
import org.spree.vkscheduler.exception.VkProcedureException;

public class GetGroupsProcedure implements VkProcedure {

    public static final String STORAGE_FUNCTION_NAME = "getGroups";

    private final VkApiClient client;
    private final VkUserActorAuthentication authentication;

    public GetGroupsProcedure(VkApiClient client, VkUserActorAuthentication authentication) {
        this.client = client;
        this.authentication = authentication;
    }

    @Override
    public JsonElement execute(String text) {
        ExecuteStorageFunctionQuery query = client.execute().storageFunction(authentication.actor(), STORAGE_FUNCTION_NAME);
        query.unsafeParam("text", text);;
        try {
            return query.execute();
        } catch (ApiException | ClientException e) {
            throw new VkProcedureException(e);
        }
    }
}
