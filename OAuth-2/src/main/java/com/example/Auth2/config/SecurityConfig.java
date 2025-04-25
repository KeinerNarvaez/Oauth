package com.example.Auth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import com.example.Auth2.service.customOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private customOauth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
                        autRequest -> autRequest
                                        .requestMatchers("/api/v1/user/").permitAll()
                                        .requestMatchers("/hello").permitAll()
                                        .requestMatchers("/oauth2/authorization/**").permitAll()
                                        .requestMatchers("/login/oauth2/code/**").permitAll()
                                        .requestMatchers("/oauth2/authorization/**").permitAll()
                                        .anyRequest().authenticated())
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // .formLogin(Customizer.withDefaults())
                // .oauth2Login(oauth2 -> oauth2
                // .userInfoEndpoint(userInfo -> userInfo
                // .userService(customOAuth2UserService)
                // )
                // .defaultSuccessUrl("http://127.0.0.1:5500/user.html", true)
                // )
                .build();
    }

}
