package com.example.Auth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Auth2.service.customOauth2UserService;
import com.example.Auth2.service.jwt.jwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
        private final AuthenticationProvider authProvider;
        private final jwtAuthenticationFilter jwtAuthenticationFilter;
        @Autowired
        @Lazy
        private customOauth2UserService customOAuth2UserService;
        @Autowired
        @Lazy
        private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(
                                                autRequest -> autRequest
                                                                .requestMatchers("/api/v1/public/**").permitAll()
                                                                .requestMatchers("/oauth2/authorization/**").permitAll()
                                                                .requestMatchers("/login/oauth2/code/**").permitAll()           
                                                                .anyRequest().authenticated())
                                // .formLogin(withDefaults())
                                // .build();
                                .sessionManagement(sessionManagement -> sessionManagement
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                                .oauth2Login(oauth2 -> oauth2
                                                .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                                                .successHandler(oAuth2LoginSuccessHandler)
                                        )
                                                                
                               

                                .authenticationProvider(authProvider)
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }
}


