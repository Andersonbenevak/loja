package com.loja.virtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.virtual.model.Acesso;
import com.loja.virtual.repository.AcessoRepository;

@Service
public class AcessoService  {
    
	@Autowired
	private AcessoRepository acessoRepository;
	
	public Acesso save (Acesso acesso) {
		return acessoRepository.save(acesso);

	}
	
}
