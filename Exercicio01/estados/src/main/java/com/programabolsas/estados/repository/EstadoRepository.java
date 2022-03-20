package com.programabolsas.estados.repository;

import com.programabolsas.estados.modelo.Estado;
import com.programabolsas.estados.modelo.RegiaoDoEstado;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

    List<Estado> findByRegiao(RegiaoDoEstado nomeRegiao, Sort sort);
}
