package org.big18.finale.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/post")
public class PostController {

    @GetMapping("/bbs")
    public String board() {
        return "bbs";
    }

    @GetMapping("/new")
    public String newPost() {
        return "write";
    }

    @PostMapping("/new")
    public String editPost() {
        return "write";
    }

}
