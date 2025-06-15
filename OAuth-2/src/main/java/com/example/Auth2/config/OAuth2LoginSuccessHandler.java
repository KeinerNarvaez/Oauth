package com.example.Auth2.config;
import com.example.Auth2.DTO.RequestLoginDTO;
import com.example.Auth2.DTO.ResponseLogin;
import com.example.Auth2.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private userService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        RequestLoginDTO loginDTO = new RequestLoginDTO();
        loginDTO.setUsername(name); // O usa email si tu sistema autentica por email
        loginDTO.setPassword("password");
        loginDTO.setEmail(email);

        ResponseLogin loginResponse = userService.login(loginDTO);

        String token = loginResponse.getToken();
        String redirectUrl = "http://127.0.0.1:5500/user.html?token=" + token;
        response.sendRedirect(redirectUrl);
    }
}
