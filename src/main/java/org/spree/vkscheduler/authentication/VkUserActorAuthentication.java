package org.spree.vkscheduler.authentication;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.UserAuthResponse;
import org.spree.core.parameter.ConfigStorage;
import org.spree.vkscheduler.exception.VkAuthenticationException;

import java.util.Date;
import java.util.logging.Logger;

import static org.spree.vkscheduler.parameter.VkParameterName.*;

public class VkUserActorAuthentication implements VkAuthentication<UserActor> {

    private final static Logger LOG = Logger.getLogger(VkUserActorAuthentication.class.getCanonicalName());

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
            setupByToken();
        }
        return actor;
    }

    @Override
    public void init(String code) {
        LOG.info("user initializing with code " + code);
        setupActor(code);
    }

    private void setupActor(String code) {
        if (tokenNotExist() || tokenExpired()) {
            setupNewToken(code);
        } else {
            LOG.info("token exist. create user by token");
            setupByToken();
        }
    }

    private void setupNewToken(String code) {
        UserAuthResponse authResponse = getUserAuthResponse(code);
        LOG.info("got authResponse: " + authResponse);
        actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        saveParameters(authResponse);
    }

    private boolean tokenNotExist() {
        return configs.getString(TOKEN) == null || configs.getString(TOKEN).isEmpty() || configs.getInt(USER_ID) == null;
    }

    private boolean tokenExpired() {
        return new Date().getTime() - configs.getInt(TOKEN_LIFETIME) > configs.getDate(TOKEN_CREATION_TIME).getTime();
    }

    private void saveParameters(UserAuthResponse authResponse) {
        configs.save(TOKEN_CREATION_TIME, new Date());
        configs.save(TOKEN_LIFETIME, authResponse.getExpiresIn());
        configs.save(TOKEN, authResponse.getAccessToken());
        configs.save(USER_ID, authResponse.getUserId());
    }

    private void setupByToken() {
        actor = new UserActor(configs.getInt(USER_ID), configs.getString(TOKEN));
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
