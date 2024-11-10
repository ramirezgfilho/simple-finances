package com.ramirezdev.simple_finances.domain.conta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {


    List<Conta> findAllByOrderByMes();
    List<Conta> findAllByMes(Integer mes);
    List<Conta> findAllByAnoAndMes(Integer ano, Integer mes);

    //Várias formas de fazer a mesma coisa, aqui com o sql puro
    @Query(
            value = "SELECT * from contas where user_id = 1 order by dataalterada desc",
            nativeQuery = true)
    List<Conta> findAllOrderDataVenc();



}
