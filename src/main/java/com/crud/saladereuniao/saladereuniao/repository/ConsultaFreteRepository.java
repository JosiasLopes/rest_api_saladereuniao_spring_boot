package com.crud.saladereuniao.saladereuniao.repository;

import com.crud.saladereuniao.saladereuniao.entity.ConsultaFrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaFreteRepository extends JpaRepository<ConsultaFrete,Long> {
}
