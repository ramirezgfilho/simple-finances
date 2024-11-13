package com.ramirezdev.simple_finances.controller;

import com.ramirezdev.simple_finances.domain.conta.ContaDTO;
import com.ramirezdev.simple_finances.domain.conta.ContaRepository;
import com.ramirezdev.simple_finances.service.ApiService;
import com.ramirezdev.simple_finances.service.ContaService;
import com.ramirezdev.simple_finances.service.RendaService;
import com.ramirezdev.simple_finances.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private RendaService rendaService;

    @Autowired
    private ApiService apiService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    private String carregaPaginaContas(Model model) {
        model.addAttribute("lista", contaService.getAllContas());
        return "contas/contas";
    }

    @GetMapping()
    private String carregaContasMesAtual(Model model, HttpServletRequest httpServletRequest) {
        //Carrega as contas do mês atual e soma o valor total, passando o valor para a tela
        var contas = contaService.findAllByAnoAndMesAndUser((LocalDate.now().getYear()), (LocalDate.now().getMonthValue()), httpServletRequest.getUserPrincipal().getName());
        Double sum = contas.stream().mapToDouble(c -> c.getValor()).sum();
        //carrega as rendas e soma os valores das rendas do mês para passar para a tela
        var listarenda = rendaService.findRendaByAnoAndMesAndUser((LocalDate.now().getYear()), (LocalDate.now().getMonthValue()), httpServletRequest.getUserPrincipal().getName());
        Double sumrenda = listarenda.stream().mapToDouble(c -> c.getValor()).sum();
        Double restantemes = (sumrenda - sum );
        //Adciona atributos para a tela.
        model.addAttribute("total", sum);
        model.addAttribute("lista", contas);
        model.addAttribute("listarendas" , listarenda);
        model.addAttribute("totalrenda" , sumrenda);
        model.addAttribute("restantemes", restantemes);
        return "contas/contas";
    }

    @GetMapping("/find/{ano}/{mes}")
    private String carregaContaMesSelecionado(@RequestParam Integer mes, @RequestParam Integer ano, Model model, HttpServletRequest httpServletRequest) {
        var contas = contaService.findAllByAnoAndMesAndUser(ano, mes, httpServletRequest.getUserPrincipal().getName());
        Double sum = contas.stream().mapToDouble(c -> c.getValor()).sum();
        var listarenda = rendaService.findRendaByAnoAndMesAndUser(ano,mes, httpServletRequest.getUserPrincipal().getName());
        Double sumrenda = listarenda.stream().mapToDouble(c -> c.getValor()).sum();
        model.addAttribute("total", sum);
        model.addAttribute("lista", contas);
        model.addAttribute("listarendas" , listarenda);
        model.addAttribute("totalrenda" , sumrenda);
        Double restantemes = (sumrenda - sum);
        model.addAttribute("restantemes", restantemes);
        return "contas/contas";
    }

    @GetMapping("/cadastro")
    private String carregaCadastro(Model model) {
        var result = apiService.obterDados("https://economia.awesomeapi.com.br/json/last/CHF-BRL");
        var valorTax = result.substring(85,89);
        Double valorcambio = Double.valueOf(valorTax);
        model.addAttribute("mesatual", LocalDate.now().getMonthValue());
        model.addAttribute("anoatual", LocalDate.now().getYear());
        model.addAttribute("valorcambio" , valorcambio);
        return "contas/cadastro";
    }

    @GetMapping("/delete/{id}")
    public String deletaConta(@RequestParam Long id){
        contaService.deleteConta(id);
        System.out.print("Deletou? ");
        return "redirect:/contas";
    }

    @PostMapping("/cadastro")
    public String createConta(@Valid ContaDTO contaDTO, HttpServletRequest httpServletRequest, BindingResult result, Model model) {
        if(result.hasErrors()){

            return "redirect:/contas/cadastro";
        } else {
            contaService.cadastraConta(contaDTO, httpServletRequest.getUserPrincipal().getName());
            return "redirect:/contas/cadastro";
        }
    }

    @GetMapping("/altera/{id}")
    public String alteraConta(@RequestParam Long id, ContaDTO contaDTO, Model model) {
        model.addAttribute("conta", contaService.findById(id));
        return "contas/altera";
    }

    @PostMapping("/altera/envia-alteracao/{id}")
    public String enviaAlteraçao(@RequestParam Long id, ContaDTO contaDTO){
        contaService.alteraConta(id, contaDTO);
        return "redirect:/contas";
    }

    @GetMapping("/cambio")
    public String confereCamobio(@RequestParam Double valor){
        return "redirect:/contas/cadastro";

    }

}
