package com.cristhiane.familymoneytrackerapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaReceita;
import com.cristhiane.familymoneytrackerapi.domain.Receita;


@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
	List<Receita> findByCategoriaReceita(CategoriaReceita categoriaReceita);
	
	List<Receita> findAllByDataBetween(LocalDate timeStart, LocalDate timeEnd);
	
	List<Receita> findByCategoriaReceitaAndDataBetween(CategoriaReceita categoriaReceita, LocalDate timeStart, LocalDate timeEnd);

}
