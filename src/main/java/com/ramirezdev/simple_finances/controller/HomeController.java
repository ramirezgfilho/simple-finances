package com.ramirezdev.simple_finances.controller;

import com.ramirezdev.simple_finances.domain.user.User;
import com.ramirezdev.simple_finances.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String carregaHomePage(HttpSession session) {
        System.out.println(session.getServletContext());
        return ("home");
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verifyUser(user);
    }

}
