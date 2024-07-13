package org.big18.finale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class maincontroller {

    @GetMapping("/main")
    public String home(){
        return "index";
    }
    @GetMapping("/detail")
    public String detail(){
        return "detail";
    }
}
