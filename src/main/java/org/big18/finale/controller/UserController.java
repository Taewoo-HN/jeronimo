package org.big18.finale.controller;

import org.big18.finale.entity.Member;
import org.big18.finale.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class UserController {

    UserService userService;

    @PostMapping("/loginprog")
    public String login(@RequestParam String user_id, @RequestParam String user_pw, Model model) {
        if (userService.login(user_id, user_pw)) {
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Member member, Model model) {
        try {
            String user_id = Objects.requireNonNull(model.getAttribute("user_id")).toString();
            String user_pw = Objects.requireNonNull(model.getAttribute("user_pw")).toString();
            String user_name = Objects.requireNonNull(model.getAttribute("user_name")).toString();
            String u_phonenum = Objects.requireNonNull(model.getAttribute("u_phonenum")).toString();
            String nickname = Objects.requireNonNull(model.getAttribute("nickname")).toString();
            String stock_account = Objects.requireNonNull(model.getAttribute("stock_account")).toString();
            String secret_key = Objects.requireNonNull(model.getAttribute("secret_key")).toString();
            if (user_id.isEmpty() || user_pw.isEmpty() || user_name.isEmpty() || u_phonenum.isEmpty()
                    || nickname.isEmpty() || stock_account.isEmpty() || secret_key.isEmpty())
                throw new Exception();
            member = new Member(user_id, user_pw, user_name, u_phonenum, nickname, stock_account, secret_key);
            userService.join(member, model);
        } catch (Exception e) {
            model.addAttribute("title", "회원가입 실패");
            model.addAttribute("error", "회원가입에 실패했습니다.");
            model.addAttribute("icon", "error");
            model.addAttribute("url", "/login");
        }
        return "message";
    }
}
