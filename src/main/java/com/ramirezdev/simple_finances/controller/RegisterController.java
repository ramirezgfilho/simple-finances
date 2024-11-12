package com.ramirezdev.simple_finances.controller;

import com.ramirezdev.simple_finances.domain.user.User;
import com.ramirezdev.simple_finances.domain.user.UserDTO;
import com.ramirezdev.simple_finances.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registro(){
        return "register/register";
    }

    @PostMapping
    @Transactional
    public String createUser(UserDTO dados) {
        userService.registerUser(dados);
        return "redirect:/login";
    }



}
