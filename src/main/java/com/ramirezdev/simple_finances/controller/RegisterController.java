package com.ramirezdev.simple_finances.controller;

import com.ramirezdev.simple_finances.domain.user.UserDTO;
import com.ramirezdev.simple_finances.service.RegisterService;
import com.ramirezdev.simple_finances.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;


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

    @GetMapping("/recuperar-senha")
    public String recuperarSenha(@RequestParam (required = false)String email, Model model) {
        return "register/recuperarsenha";
    }

    @PostMapping("recuperar-senha")
    public String recuperaASenha(@RequestParam(required = false) String email, Model model) {

        if (email != null) {
            String mensagem = "Se existir algum usuário com o e-mail: " + email + " uma nova senha será enviada por e-mail";
            model.addAttribute("mensagem", mensagem);
            registerService.handlePasswordRecovery(email);
            return "register/recuperarsenha";
        } else {
            String mensagem = null;
            model.addAttribute("mensagem", mensagem);
            return "register/recuperarsenha";
        }
    }






}
