package org.big18.finale.controller;

import jakarta.servlet.http.HttpSession;
import org.big18.finale.service.UserNameProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecommendController {

    private final UserNameProvider userNameProvider;

    public RecommendController(UserNameProvider userNameProvider) {
        this.userNameProvider = userNameProvider;
    }

    @GetMapping("/recommend")
    public String recommend(Model model, HttpSession session) {

        userNameProvider.setUserAttributes(session, model);
        return "recommend";
    }
}
