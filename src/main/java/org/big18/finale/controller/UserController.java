package org.big18.finale.controller;

import org.big18.finale.repository.MemberRepository;
import org.big18.finale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class UserController {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/logging")
    public ResponseEntity<?> loginPage(@RequestParam("user_id") String user_id, @RequestParam("user_pw") String user_pw) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user_id, user_pw));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(Map.of("success", true, "message", "로그인 성공"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "로그인 실패" + e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam("user_id") String user_id, @RequestParam("user_pw") String user_pw,
                                      @RequestParam("user_name") String userName, @RequestParam("phone_number") String phoneNumber,
                                      @RequestParam("email") String email) {
        try {
            String encodePw = passwordEncoder.encode(user_pw);
            userService.register(user_id, encodePw, email, userName, phoneNumber);
            return ResponseEntity.ok().body(Map.of("success", true, "message", "회원가입 성공"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "회원가입 실패" + e.getMessage()));
        }
    }
    @RequestMapping("/auth/naver")
    public class NaverOAuth2Controller {

        @GetMapping("")
        public ResponseEntity<String> naverCallback(@RegisteredOAuth2AuthorizedClient("naver") OAuth2AuthorizedClient authorizedClient,
                                                    @AuthenticationPrincipal OAuth2User oauth2User) {
            OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
            Map<String, Object> attributes = oauth2User.getAttributes();

            // 여기서 사용자 정보를 처리하고 필요한 로직을 구현합니다.
            String email = (String) attributes.get("email");
            String name = (String) attributes.get("name");

            // 로그인 성공 후 처리 로직
            // 예: JWT 토큰 생성, 사용자 정보 저장 등

            return ResponseEntity.ok("네이버 로그인 성공: " + name);
        }
    }

}



