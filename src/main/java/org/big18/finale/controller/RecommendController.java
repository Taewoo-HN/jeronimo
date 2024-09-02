package org.big18.finale.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecommendController {

    private final HttpSession session;

    @Autowired
    public RecommendController(HttpSession session) {
        this.session = session;
    }

    @GetMapping("/recommend")
    public String recommend(Model model) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            username = "guest";
        }
        boolean isGuest = username.equals("guest");

        model.addAttribute("username", username);
        model.addAttribute("isGuest", isGuest);

        return "recommend";
    }
}
