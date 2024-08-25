package org.big18.finale.controller;

import org.big18.finale.entity.Users;
import org.big18.finale.repository.MemberRepository;
import org.big18.finale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class UserController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/logging")
    public ResponseEntity<Map<String, Object>> loginPage(@RequestParam("user_id") String user_id, @RequestParam("user_pw") String user_pw, @RequestParam("_csrf") String csrfToken) {
        try {
            // 아이디와 비밀번호를 데이터베이스에서 조회
            Users user = (Users) userService.loadUserByUsername(user_id);

            // 아이디가 존재하지 않는 경우
            if (user == null) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "아이디가 존재하지 않습니다."));
            }

            // 비밀번호가 일치하지 않는 경우
            if (!passwordEncoder.matches(user_pw, user.getUserPw())) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "비밀번호가 일치하지 않습니다."));
            }

            // 인증 성공
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



