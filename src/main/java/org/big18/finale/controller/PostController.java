package org.big18.finale.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {


    private final HttpSession session;

    @Autowired
    public PostController(HttpSession session) {
        this.session = session;
    }


    @GetMapping("/bbs")
    public String board(Model model) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            username = "guest";
        }
        boolean isGuest = username.equals("guest");

        model.addAttribute("username", username);
        model.addAttribute("isGuest", isGuest);


        return "post/bbs";
    }

    @GetMapping("/new")
    public String newPost() {
        return "post/write";
    }

    @PostMapping("/new")
    public String editPost() {
        return "post/write";
    }

}
