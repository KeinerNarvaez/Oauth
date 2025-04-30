package com.example.Auth2.config;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.Auth2.service.CustomOAuth2User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
        String token = oauthUser.getJwtToken();

        String redirectUrl = "http://127.0.0.1:5500/user.html?token=" + URLEncoder.encode(token, StandardCharsets.UTF_8);

        response.sendRedirect(redirectUrl);
    }
}