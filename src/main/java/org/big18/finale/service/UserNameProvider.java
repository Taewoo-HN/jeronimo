package org.big18.finale.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserNameProvider {

    public String getUsername(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "guest";
        }
        return username;
    }

    public boolean isGuest(String username) {
        return "guest".equals(username);
    }

    public void setUserAttributes(HttpSession session, Model model) {
        String username = getUsername(session);
        boolean isGuest = isGuest(username);

        model.addAttribute("username", username);
        model.addAttribute("isGuest", isGuest);
    }
}
