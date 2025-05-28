package com.example.Auth2.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.Auth2.DTO.userDTO;

@Service
public class customOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private userService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String clientName = userRequest.getClientRegistration().getClientName(); 
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = null;
        String name = null;

        if ("Google".equalsIgnoreCase(clientName)) {
            email = (String) attributes.get("email");
            name = (String) attributes.get("name");
        } else if ("GitHub".equalsIgnoreCase(clientName)) {
            email = (String) attributes.get("login");
            name = (String) attributes.get("name");
        } else if ("Facebook".equalsIgnoreCase(clientName)) {
            email = (String) attributes.get("email");
            name = (String) attributes.get("name");
        }
        System.out.println("Client Name: " + clientName);
        System.out.println("Email: " + email);
        System.out.println("Name: " + name);
        if (email != null && !email.isEmpty()) {
            boolean userExists = !userService.getListUserForName(email).isEmpty();

            if (!userExists) {
                userDTO newUser = new userDTO(
                    0,                                      
                    Objects.requireNonNullElse(name, "Unknown"), 
                    email,                               
                    LocalDateTime.now(),                
                    true                                     
                );

                userService.save(newUser);
            }
        }

        return oAuth2User;
    }
}