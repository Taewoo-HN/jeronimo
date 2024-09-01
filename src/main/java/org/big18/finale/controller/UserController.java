package org.big18.finale.controller;

import org.big18.finale.repository.MemberRepository;
import org.big18.finale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder, UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestParam("user_id") String user_id, @RequestParam("user_pw") String user_pw,
                                      @RequestParam("user_name") String userName, @RequestParam("phone_number") String phoneNumber,
                                      @RequestParam("email") String email, Model model) {
        try {
            userService.register(user_id, user_pw, email, userName, phoneNumber);
            model.addAttribute("success","회원가입 완료");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}




