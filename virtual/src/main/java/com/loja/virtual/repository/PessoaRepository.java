package com.loja.virtual.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.loja.virtual.model.Pessoa;
import com.loja.virtual.model.PessoaJuridica;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
	
	@Query(value = "select pj from PessoaJuridica pj where pj.cnpj= ?1")
	public PessoaJuridica ExisteCnpjCadastrado(String cnpj);

}
