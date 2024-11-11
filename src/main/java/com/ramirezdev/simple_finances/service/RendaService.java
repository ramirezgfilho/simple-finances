package com.ramirezdev.simple_finances.service;


import com.ramirezdev.simple_finances.domain.renda.Renda;
import com.ramirezdev.simple_finances.domain.renda.RendaDTO;
import com.ramirezdev.simple_finances.domain.renda.RendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendaService {

    @Autowired
    RendaRepository rendaRepository;

    @Autowired
    UserService userService;

    public List<Renda> findAll() {
        return rendaRepository.findAll();
    }


    public void insereRenda(RendaDTO rendaDTO, String login) {
        RendaDTO rendaDTOn = new RendaDTO(rendaDTO.descricao(), rendaDTO.valor(), userService.findByLogin(login), rendaDTO.mes(), rendaDTO.ano());
        Renda renda = new Renda(rendaDTOn);
        rendaRepository.save(renda);
    }


    public List<Renda> findRendaByAnoAndMes(Integer ano, Integer mes) {
        return rendaRepository.findAllByAnoAndMes(ano, mes);
    }

    public List<Renda> findRendaByAnoAndMesAndUser(Integer ano, Integer mes, String user) {
        return rendaRepository.findAllByAnoAndMesAndUser(ano, mes, userService.findByLogin(user));
    }



}
