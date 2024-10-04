package org.big18.finale.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserNameProvider {

    public static final String GUEST_USERNAME = "guest";

    public String getUsername(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return username != null ? username : GUEST_USERNAME;
    }

    public boolean isGuest(String username) {
        return GUEST_USERNAME.equals(username);
    }

    public void setUserAttributes(HttpSession session, Model model) {
        String username = getUsername(session);
        boolean isGuest = isGuest(username);

        model.addAttribute("username", username);
        model.addAttribute("isGuest", isGuest);
    }

    // 게시물 작성을 위한 사용자 검증 메서드 추가
    public boolean canWritePost(HttpSession session) {
        return !isGuest(getUsername(session));
    }
}