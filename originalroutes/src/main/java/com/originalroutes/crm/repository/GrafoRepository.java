package com.originalroutes.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.originalroutes.crm.model.Grafo;

@Repository
public interface GrafoRepository extends JpaRepository<Grafo, Long>{
	default Grafo findOne(Long id) { 
        return (Grafo) findById(id).orElse(null); 
    } 
}
