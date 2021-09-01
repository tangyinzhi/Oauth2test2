package com.oauth2.pojo;

import org.springframework.stereotype.Component;

@Component

public class Oauth2Property {
    private String clientId = "237c86f456d10b96f40b";
    private String clientSecret = "969e44221c86eadf8e788c4529578462abc06171";
    private String authorizeUrl = "https://github.com/login/oauth/authorize";
    private String redirectUrl = "http://localhost:8080/authorization_code";
    private String accessTokenUrl = "https://github.com/login/oauth/access_token";
    private String userInfoUrl = "https://api.github.com/user";


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public void setAuthorizeUrl(String authorizeUrl) {
        this.authorizeUrl = authorizeUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getUserInfoUrl() {
        return userInfoUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }
}
