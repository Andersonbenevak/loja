package com.loja.virtual;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.loja.virtual.controller.PessoaController;
import com.loja.virtual.enums.TipoEndereco;
import com.loja.virtual.model.Endereco;
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
		
		pessoaJuridica.setName("ADSB SSIVAA LVES");
		pessoaJuridica.setCategoria("BENEVAK BENEVAK");
		pessoaJuridica.setCnpj(""+ Calendar.getInstance().getTimeInMillis());
		pessoaJuridica.setEmail("ANDSONBENEVAKS82020@gmail.com");
        pessoaJuridica.setInscEstadual("64696");
        pessoaJuridica.setInscMunicipal("9999/53");
        pessoaJuridica.setNomeFantasia("B TIHNAS");
        pessoaJuridica.setRazaoSocial("ANDERSON SDTR S BATISTA");
        pessoaJuridica.setTelefone("4029-8908");
        pessoaJuridica.setEmpresa(pessoaJuridica);
        
        	Endereco endereco1 = new Endereco();
        	endereco1.setBairro("Cidade 2");
        	endereco1.setCep("132573-03");
        	endereco1.setCidade("salto DE IYU");
        	endereco1.setComplemento("casa amarela");
        	endereco1.setNumero("47");
        	endereco1.setPessoa(pessoaJuridica);
        	endereco1.setRuaLogra("Rua cabreuva de itu");
        	endereco1.setTipoEndereco(TipoEndereco.CONBRANCA);
        	endereco1.setUf("SP");
        	
        	Endereco endereco2 = new Endereco();
        	endereco2.setBairro("Cidade 3");
        	endereco2.setCep("1322773-05");
        	endereco2.setCidade("salto");
        	endereco2.setPessoa(pessoaJuridica);
        	endereco2.setComplemento("casa azul e amarela");
        	endereco2.setNumero("71");
        	endereco2.setRuaLogra("Rua campinas de saltp");
        	endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
        	endereco2.setUf("SP");
        	
        	pessoaJuridica.getEnderecos().add(endereco1);
        	pessoaJuridica.getEnderecos().add(endereco2);
        	
        
           pessoaJuridica =  pessoaController.salvarPJ(pessoaJuridica).getBody();
           
           assertEquals(true, pessoaJuridica.getId() > 0);
           
           for (Endereco endereco: pessoaJuridica.getEnderecos()) {
        	   assertEquals(true, endereco.getId() > 0);
        	   
           }
           
           assertEquals(2, pessoaJuridica.getEnderecos().size());
	}
	

}
