package com.loja.virtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.server.header.XContentTypeOptionsServerHttpHeadersWriter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.loja.virtual.ExceptionLojaJava;
import com.loja.virtual.model.PessoaJuridica;
import com.loja.virtual.repository.PessoaRepository;
import com.loja.virtual.service.PessoaUserService;

import net.bytebuddy.asm.Advice.Return;


@RestController
public class PessoaController {
	
    @Autowired
	private PessoaRepository pessoaRepository;
    
    @Autowired
    private PessoaUserService pessoaUserService;
	
	@ResponseBody
	@PostMapping("salvarPj")
	public ResponseEntity<PessoaJuridica>salvarPJ(@RequestBody PessoaJuridica pessoajuridica) throws ExceptionLojaJava{
	
		
		if(pessoajuridica== null) {
			throw new ExceptionLojaJava("Pessoa juridica não pode ser NULL");
		}
		
		if(pessoajuridica.getId() ==null && pessoaRepository.ExisteCnpjCadastrado(pessoajuridica.getCnpj()) != null) {
			throw new ExceptionLojaJava("Já existe esse cnpj cadastrado com esse numero" + pessoajuridica.getCnpj());			
		}
		
		pessoajuridica = pessoaUserService.salvarPessoaJuridica(pessoajuridica);
		
		return new ResponseEntity<PessoaJuridica>(pessoajuridica, HttpStatus.OK);
	}
	
	

}
