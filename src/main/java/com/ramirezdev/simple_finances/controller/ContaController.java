package com.ramirezdev.simple_finances.controller;

import com.ramirezdev.simple_finances.domain.conta.ContaDTO;
import com.ramirezdev.simple_finances.domain.conta.ContaRepository;
import com.ramirezdev.simple_finances.service.ApiService;
import com.ramirezdev.simple_finances.service.ContaService;
import com.ramirezdev.simple_finances.service.RendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


@Controller
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaRepository repository;

    @Autowired
    private RendaService rendaService;

    @Autowired
    private ApiService apiService;

    @GetMapping("/all")
    private String carregaPaginaContas(Model model) {
        model.addAttribute("lista", contaService.getAllContas());
        return "contas/contas";
    }

    @GetMapping()
    private String carregaContasMesAtual(Model model) {
        //Carrega as contas do mês atual e soma o valor total, passando o valor para a tela
        var contas = repository.findAllByAnoAndMes((LocalDate.now().getYear()), (LocalDate.now().getMonthValue()));
        Double sum = contas.stream().mapToDouble(c -> c.getValor()).sum();

        //carrega as rendas e soma os valores das rendas do mês para passar para a tela
        var listarenda = rendaService.findRendaByAnoAndMes((LocalDate.now().getYear()), (LocalDate.now().getMonthValue()));
        Double sumrenda = listarenda.stream().mapToDouble(c -> c.getValor()).sum();
        Double restantemes = (sumrenda - sum );

        model.addAttribute("total", sum);
        model.addAttribute("lista", contas);
        model.addAttribute("listarendas" , listarenda);
        model.addAttribute("totalrenda" , sumrenda);
        model.addAttribute("restantemes", restantemes);
        return "contas/contas";
    }

    @GetMapping("/find/{ano}/{mes}")
    private String carregaContaMesSelecionado(@RequestParam Integer mes, @RequestParam Integer ano, Model model) {
        var contas = repository.findAllByAnoAndMes(ano, mes);
        Double sum = contas.stream().mapToDouble(c -> c.getValor()).sum();
        var listarenda = rendaService.findRendaByAnoAndMes(ano,mes);
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
        model.addAttribute("valorcambio" , valorcambio);
        return "contas/cadastro";
    }

    @GetMapping("/delete/{id}")
    public String deletaConta(@RequestParam Long id){
        contaService.deleteConta(id);
        System.out.print("Deletou? ");
        return "redirect:/contas";
    }

    @PostMapping("/insereConta")
    public String createConta(ContaDTO dados) {
        contaService.cadastraConta(dados);
        return "redirect:/contas/cadastro";
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

//
//@RequestMapping(value = "/employees", method = RequestMethod.POST)
//public Employee createEmployee(@RequestBody Employee emp){
//    return empService.createEmployee(emp);
//}
