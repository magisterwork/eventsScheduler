package org.spree.vkscheduler.procedure;

import com.google.gson.JsonElement;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.queries.execute.ExecuteStorageFunctionQuery;
import org.spree.vkscheduler.authentication.VkUserActorAuthentication;
import org.spree.vkscheduler.exception.VkProcedureException;

import java.util.HashMap;
import java.util.Map;

public class GetGroupsProcedure implements VkProcedure<GetGroupsProcedure.Query> {

    public static final String STORAGE_FUNCTION_NAME = "getGroups";

    private final VkApiClient client;
    private final VkUserActorAuthentication authentication;

    public GetGroupsProcedure(VkApiClient client, VkUserActorAuthentication authentication) {
        this.client = client;
        this.authentication = authentication;
    }

    @Override
    public JsonElement execute(Query query) {
        ExecuteStorageFunctionQuery storageQuery = client.execute().storageFunction(authentication.actor(), STORAGE_FUNCTION_NAME);
        query.params().forEach(storageQuery::unsafeParam);

        try {
            return storageQuery.execute();
        } catch (ApiException | ClientException e) {
            throw new VkProcedureException(e);
        }
    }

    public static class Query implements VkProcedure.Query {

        private HashMap<String, Object> params = new HashMap<>();

        public Query(String text, Integer cityId) {
            params.put("text", text);
            params.put("cityId", cityId);
        }

        @Override
        public Map<String, Object> params() {
            return params;
        }
    }
}
