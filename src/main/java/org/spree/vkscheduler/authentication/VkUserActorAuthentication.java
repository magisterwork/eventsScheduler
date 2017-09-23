package org.spree.vkscheduler.authentication;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.UserAuthResponse;
import org.spree.core.parameter.ConfigStorage;
import org.spree.vkscheduler.exception.VkAuthenticationException;

import static org.spree.vkscheduler.parameter.VkParameterName.*;

public class VkUserActorAuthentication implements VkAuthentication<UserActor> {

    private final ConfigStorage configs;
    private final VkApiClient vkClient;

    private UserActor actor;

    public VkUserActorAuthentication(ConfigStorage configs, VkApiClient vkClient) {
        this.configs = configs;
        this.vkClient = vkClient;
    }

    @Override
    public UserActor actor() {
        if (actor == null) {
            throw new VkAuthenticationException("vk actor is null now. should init authentication");
        }
        return actor;
    }

    @Override
    public void init(String code) {
        setupActor(code);
    }

    private void setupActor(String code) {
        if (configs.getString(TOKEN) == null || configs.getInt(USER_ID) == null) {
            UserAuthResponse authResponse = getUserAuthResponse(code);
            actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
            configs.save(TOKEN, authResponse.getAccessToken());
            configs.save(USER_ID, authResponse.getUserId());
        }
    }

    private UserAuthResponse getUserAuthResponse(String code) {
        return getUserAuthResponse(code,
                configs.getInt(CLIENT_ID),
                configs.getString(SECRET_KEY),
                configs.getString(REDIRECT_URI)
        );
    }

    private UserAuthResponse getUserAuthResponse(String code, int appId, String clientSecret, String redirectUri) {
        try {
            return vkClient
                    .oauth()
                    .userAuthorizationCodeFlow(appId, clientSecret, redirectUri, code)
                    .execute();
        } catch (ApiException | ClientException e) {
            throw new VkAuthenticationException("vk auth fail", e);
        }
    }
}