package org.big18.finale.controller;

import org.big18.finale.repository.MemberRepository;
import org.big18.finale.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class UserController {


    private final BCryptPasswordEncoder passwordEncoder;

    private final UserService userService;

    public UserController(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
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




