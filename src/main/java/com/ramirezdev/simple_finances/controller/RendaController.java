package com.ramirezdev.simple_finances.controller;

import com.ramirezdev.simple_finances.domain.renda.RendaDTO;
import com.ramirezdev.simple_finances.service.RendaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/renda")
public class RendaController {



    @Autowired
    RendaService rendaService;

    @GetMapping()
    private String carregaRendaMesAtual(Model model) {
        var rendas = rendaService.findAll();
        model.addAttribute("listarendas", rendas);
        return "fragments/renda";
    }

    @PostMapping("/insere")
    private String insereRenda(RendaDTO rendaDTO, HttpServletRequest httpServletRequest) {
        rendaService.insereRenda(rendaDTO, httpServletRequest.getUserPrincipal().getName());
        return "redirect:/contas";
    }

}
