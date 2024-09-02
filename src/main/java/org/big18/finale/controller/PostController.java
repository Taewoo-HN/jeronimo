package org.big18.finale.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    @GetMapping("/bbs")
    public String board() {
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
