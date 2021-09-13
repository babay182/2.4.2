package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
@Transactional
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String formlogin() {
        return "/login";
    }

    @GetMapping("/users/user")
    public String userProfile(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByName(principal.getName()));
        return "admin/show";
    }
}
