package com.example.Auth2.service;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private OAuth2User oauth2User;
    private String jwtToken;

    public CustomOAuth2User(OAuth2User oauth2User, String jwtToken) {
        this.oauth2User = oauth2User;
        this.jwtToken = jwtToken;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getName();
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
 