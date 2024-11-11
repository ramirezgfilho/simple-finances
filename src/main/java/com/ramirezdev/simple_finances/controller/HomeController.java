package com.ramirezdev.simple_finances.controller;

import com.ramirezdev.simple_finances.domain.user.User;
import com.ramirezdev.simple_finances.domain.user.UserDTO;
import com.ramirezdev.simple_finances.domain.user.UserReposiroty;
import com.ramirezdev.simple_finances.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;


    @Autowired
    private UserReposiroty userReposiroty;

//    @GetMapping
//    public ResponseEntity helloPage(){
//        var helloPage = userReposiroty.findAll().stream();
//        return ResponseEntity.ok(helloPage);
//    }

    @GetMapping
    public String carregaHomePage(HttpSession session) {
        System.out.println(session.getId());
        return ("home");
    }


    @PostMapping
    @Transactional
    public String createUser(UserDTO dados) {
        var usuario = new User(dados);
        userReposiroty.save(usuario);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verifyUser(user);
    }

}
