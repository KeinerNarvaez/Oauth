package com.example.Auth2.service;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


import com.example.Auth2.DTO.userDTO;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class customOauth2UserService extends DefaultOAuth2UserService {

@Autowired
private userService userService;

@Override
public OAuth2User loadUser(OAuth2UserRequest userRequest) {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    String clientName = userRequest.getClientRegistration().getClientName(); // "Google", "GitHub", etc.
    Map<String, Object> attributes = oAuth2User.getAttributes();

    String email = null;
    String name = null;

    if ("Google".equals(clientName)) {
        email = (String) attributes.get("email");
        name = (String) attributes.get("name");
    } else if ("GitHub".equals(clientName)) {
        email = (String) attributes.get("email");
        name = (String) attributes.get("login");
    }

    if (email != null && userService.getListUserForName(email).isEmpty()) {
        userDTO newUser = new userDTO(0, name, email, null, true) ;
        newUser.setEmail(email);
        newUser.set_breedName(name);

        userService.save(newUser);
    }

    return oAuth2User;
}

}
