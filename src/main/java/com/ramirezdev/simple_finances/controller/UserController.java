package com.ramirezdev.simple_finances.controller;

import com.ramirezdev.simple_finances.domain.user.User;
import com.ramirezdev.simple_finances.domain.user.UserDTO;
import com.ramirezdev.simple_finances.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String carregaUsuarios(Model model) {
        model.addAttribute("lista", userService.findAll());
        return "users/listagem";
    }

    @GetMapping("/cadastro")
    public String cadastraUsuario(){
        return "users/cadastro";
    }

    @PostMapping
    @Transactional
    public String createUser(UserDTO dados) {
        userService.registerUser(dados);
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


    @GetMapping("/altera-senha")
    public String alteraSenha(@RequestParam (required = false)String senhaatual,String novasenha, Model model) {
        return"users/alterasenha";
    }

    @PostMapping("/altera-senha")
    public String salvaSenha(@RequestParam (required = false)String senhaatual,String novasenha, Model model, HttpServletRequest httpServletRequest) {
        boolean alterada;
        if (userService.alteraSenha(senhaatual, novasenha, httpServletRequest.getUserPrincipal().getName())) {
            String message = "Senha alterada com sucesso!";
            alterada = true;
            model.addAttribute("mensagem", message);
            model.addAttribute("alterada", alterada);
            return "users/alterasenha";
        } else {
            String message = "Senha não alterada, a senha atual está incorreta.";
            alterada = false;
            model.addAttribute("alterada", alterada);
            model.addAttribute("mensagem", message);
            return "users/alterasenha";
        }

    }




}
