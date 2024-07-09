package org.big18.finale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller("/")
public class maincontroller {

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
