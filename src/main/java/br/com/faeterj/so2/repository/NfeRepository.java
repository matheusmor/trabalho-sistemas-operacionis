package br.com.faeterj.so2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.faeterj.so2.model.NfeValor;

@Repository
public interface NfeRepository extends JpaRepository<NfeValor,Integer>{

	
}
