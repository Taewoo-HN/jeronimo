package org.big18.finale.controller;

import org.big18.finale.repository.MemberRepository;
import org.big18.finale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}



