package org.big18.finale.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/main", "/login", "logging", "/register",
                                "/oauth2/code/naver", "/callback","/bbs"
                                , "/detail", "/logout", "/recommend"
                                , "/js/**", "/img/**", "/css/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((formlogin) -> formlogin
                        .loginPage("/login")
                        .loginProcessingUrl("/logging")
                        .defaultSuccessUrl("/main")
                        .failureForwardUrl("/login?error")
                        .usernameParameter("user_id")
                        .passwordParameter("user_pw")
                        .permitAll()

                ).oauth2Login((oauth2Login) -> oauth2Login
                        .loginPage("/oauth2/code/naver")
                        .defaultSuccessUrl("/callback")
                        .failureUrl("/login?error")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest
                .toStaticResources()
                .atCommonLocations());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return (userRequest) -> {
            OAuth2User oauth2User = delegate.loadUser(userRequest);

            // 네이버 로그인 처리
            if ("naver".equals(userRequest.getClientRegistration().getRegistrationId())) {
                Map<String, Object> attributes = oauth2User.getAttributes();
                Map<String, Object> response = (Map<String, Object>) attributes.get("response");

                String nameAttributeKey = "id";
                attributes.putAll(response);

                return new DefaultOAuth2User(
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                        attributes,
                        nameAttributeKey
                );
            }
            return oauth2User;
        };
    }
}




