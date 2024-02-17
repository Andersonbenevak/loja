package com.loja.virtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.loja.virtual.controller.PessoaController;
import com.loja.virtual.model.PessoaJuridica;

import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = VirtualApplication.class)

public class testCadPessoaFisica extends TestCase{
	

	
	@Autowired
	private PessoaController pessoaController;
	
	@Test
	public void testCadPessoaFisica() throws ExceptionLojaJava {
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		
		pessoaJuridica.setName("BRUNA SILVA ");
		pessoaJuridica.setCategoria("B BENEVAK");
		pessoaJuridica.setCnpj("966-61");
		pessoaJuridica.setEmail("andersonbe2020@gmail.com");
        pessoaJuridica.setInscEstadual("645696");
        pessoaJuridica.setInscMunicipal("9999256/535");
        pessoaJuridica.setNomeFantasia("B TINAS");
        pessoaJuridica.setRazaoSocial("ANDERSON  b S BATISTA");
        pessoaJuridica.setTelefone("4029-8908");
        pessoaJuridica.setEmpresa(pessoaJuridica);
        
        pessoaController.salvarPJ(pessoaJuridica);
	}
	

}
