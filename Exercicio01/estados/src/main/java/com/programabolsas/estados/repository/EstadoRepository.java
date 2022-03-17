package com.programabolsas.estados.repository;

import com.programabolsas.estados.modelo.Estado;
import com.programabolsas.estados.modelo.RegiaoDoEstado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

    Page<Estado> findByRegiao(RegiaoDoEstado nomeRegiao, Pageable paginacao);
}
