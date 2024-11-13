package com.ramirezdev.simple_finances.controller;

import com.ramirezdev.simple_finances.domain.user.User;
import com.ramirezdev.simple_finances.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController implements WebMvcConfigurer {

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String carregaDashboard(HttpSession session, HttpServletRequest httpServletRequest, Model model) {
        Principal principal = httpServletRequest.getUserPrincipal();
        model.addAttribute("login", principal.getName());
        return ("dashboard");
    }

    @GetMapping()
    public String carregaHome() {
        return "dashboard";
    }

    @GetMapping("/login")
    public String carregaLogin(){
        return "login";
    }



}
