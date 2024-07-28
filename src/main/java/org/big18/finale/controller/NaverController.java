package org.big18.finale.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class NaverController {

        @GetMapping("/oauth2/code/naver")
        public String naverLogin() {
            return "naverlogin";
        }

        @PostMapping("/callback")
        public ResponseEntity<String> naverCallback(@RegisteredOAuth2AuthorizedClient("naver") OAuth2AuthorizedClient authorizedClient,
                                                    @AuthenticationPrincipal OAuth2User oauth2User) {
            OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
            Map<String, Object> attributes = oauth2User.getAttributes();


            String email = (String) attributes.get("email");
            String name = (String) attributes.get("name");
            String phone_number = (String) attributes.get("phone_number");


            return ResponseEntity.ok("네이버 로그인 성공: " + name);
        }
    }
