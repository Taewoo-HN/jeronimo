package org.big18.finale.controller;

import org.big18.finale.entity.Users;
import org.big18.finale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUserProfile(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Users user = userService.getUserById(principal.getName());
        model.addAttribute("user", user);
        return "profile"; // profile.mustache 템플릿을 사용
    }

    @GetMapping("/api")
    public ResponseEntity<Users> getUserProfileApi(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Users user = userService.getUserById(principal.getName());
        return ResponseEntity.ok(user);
    }
}