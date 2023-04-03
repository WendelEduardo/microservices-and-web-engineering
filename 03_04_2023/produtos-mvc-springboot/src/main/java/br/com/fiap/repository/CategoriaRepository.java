package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.CategoriaModel;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long>{
	
}