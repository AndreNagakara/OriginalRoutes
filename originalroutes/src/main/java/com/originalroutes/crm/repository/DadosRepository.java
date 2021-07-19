package com.originalroutes.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.originalroutes.crm.model.Dados;

@Repository
public interface DadosRepository extends JpaRepository<Dados, Long>{
	default Dados findOne(Long id) { 
        return (Dados) findById(id).orElse(null); 
    } 
}
