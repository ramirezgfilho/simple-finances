package com.ramirezdev.simple_finances.service;

import com.ramirezdev.simple_finances.domain.conta.Conta;
import com.ramirezdev.simple_finances.domain.conta.ContaDTO;
import com.ramirezdev.simple_finances.domain.conta.ContaDTOPagar;
import com.ramirezdev.simple_finances.domain.conta.ContaRepository;
import com.ramirezdev.simple_finances.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UserService userService;


    public Object getAllContas() {
        //Aqui daria pra usar assim também
        //return contaRepository.findAll(Sort.by(Sort.Direction.DESC, "dataalterada"));
        return contaRepository.findAllByOrderByMes();
    }

    public Object getContasMesCorrente(){
        var valor = contaRepository.findAllByMes(LocalDate.now().getMonthValue());
        return contaRepository.findAllByMes(LocalDate.now().getMonthValue());
    }

    public Object getContasMesSelecionado(Integer ano, Integer mes) {
        var contas = contaRepository.findAllByAnoAndMes(ano, mes);
        return contas;
    }


    public void cadastraConta(ContaDTO dados, String username) {
        ContaDTO contaDTO = new ContaDTO(dados.descricao(), dados.valor(), Date.valueOf(LocalDate.now()), userService.findByLogin(username), dados.mes(), dados.ano(), dados.pago());
        var conta = new Conta(contaDTO);
        contaRepository.save(conta);
    }

//    public void pagaConta(ContaDTO contaDTOAlterar) {
//        System.out.println("Chegou no service!");
//        var conta = contaRepository.getReferenceById(contaDTOPagar.id());
//        conta.pagaConta(contaDTOAlterar);
//        contaRepository.save(conta);
//        System.out.println(conta);
//    }

    public void deleteConta(Long id){
        contaRepository.deleteById(id);
    }

    public Conta findById(Long id) {
        return contaRepository.getReferenceById(id);
    }


    public void alteraConta(Long id, ContaDTO contaDTO) {
        var conta = contaRepository.getReferenceById(id);
        conta.atualizaConta(contaDTO);
        contaRepository.save(conta);
    }

    public List<Conta> findAllByAnoAndMesAndUser(Integer ano, Integer mes, String user) {
        return contaRepository.findAllByAnoAndMesAndUser(ano, mes, userService.findByLogin(user));
    }
}
