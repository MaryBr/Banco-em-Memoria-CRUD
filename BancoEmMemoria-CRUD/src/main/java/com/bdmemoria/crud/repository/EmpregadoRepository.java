package com.bdmemoria.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bdmemoria.crud.entidade.Empregado;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

}
