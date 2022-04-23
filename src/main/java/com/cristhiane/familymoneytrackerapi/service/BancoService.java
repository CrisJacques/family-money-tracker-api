package com.cristhiane.familymoneytrackerapi.service;

import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.enums.Banco;

@Service
public class BancoService {	
	public Hashtable<Integer, Object> findAll() {
		int i = 0;
		Hashtable<Integer, Object> bancos = new Hashtable<Integer, Object>();
		for (Banco bnc : Banco.values()) {
			Hashtable<Object, Object> banco = new Hashtable<Object, Object>();
			
			banco.put("cod", bnc.getCod());
			banco.put("descricao", bnc.getDescricao());
			
			bancos.put(i, banco);
			i++;
		}
		
		return bancos;
	}
}