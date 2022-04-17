package com.cristhiane.familymoneytrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

}
