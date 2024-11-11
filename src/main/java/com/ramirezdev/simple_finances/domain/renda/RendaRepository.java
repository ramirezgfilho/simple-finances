package com.ramirezdev.simple_finances.domain.renda;

import com.ramirezdev.simple_finances.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendaRepository extends JpaRepository<Renda, Long> {


    List<Renda> findAllByAnoAndMes(Integer ano, Integer mes);
    List<Renda> findAllByAnoAndMesAndUser(Integer ano, Integer mes, User user);
}
