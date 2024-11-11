package com.ramirezdev.simple_finances.controller;

import com.ramirezdev.simple_finances.domain.user.User;
import com.ramirezdev.simple_finances.domain.user.UserDTO;
import com.ramirezdev.simple_finances.domain.user.UserReposiroty;
import com.ramirezdev.simple_finances.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserReposiroty userReposiroty;

    @Autowired
    private UserService userService;

    @GetMapping
    public String carregaUsuarios(Model model) {
        model.addAttribute("lista", userReposiroty.findAll());
        return "users/listagem";
    }

    @GetMapping("/cadastro")
    public String cadastraUsuario(){
        return "users/cadastro";
    }

    @PostMapping
    @Transactional
    public String createUser(UserDTO dados) {
        var usuario = new User(dados);
        userService.registerUser(usuario);
        return "redirect:/users";
    }

//    @GetMapping("/{id}")
//    public String getPerfilUsuario(@PathVariable Integer id, Model model) {
//        model.addAttribute("user",userReposiroty.getReferenceById(1L));//Criar método para buscar o usuário corrente..
//        return "users/profile";
//    }


    @Transactional
    @GetMapping("/delete/{id}")
    public String deleteUser(@RequestParam Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }




}
