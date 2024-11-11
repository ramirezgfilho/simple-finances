package com.ramirezdev.simple_finances.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {


    @GetMapping
    public String registro(){
        return "register/register";
    }


}
