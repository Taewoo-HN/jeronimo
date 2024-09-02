package org.big18.finale.controller;


import jakarta.servlet.http.HttpSession;
import org.big18.finale.service.UserNameProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    private final UserNameProvider userNameProvider;

    @Autowired
    public PostController(HttpSession session, UserNameProvider userNameProvider) {
        this.userNameProvider = userNameProvider;
    }

    @GetMapping("/bbs")
    public String board(Model model, HttpSession session) {

        userNameProvider.setUserAttributes(session, model);
        return "post/bbs";
    }

    @GetMapping("/new")
    public String newPost(Model model, HttpSession session) {
        userNameProvider.setUserAttributes(session, model);
        return "post/write";
    }

    @PostMapping("/new")
    public String editPost(Model model, HttpSession session) {
        return "post/write";
    }

}
