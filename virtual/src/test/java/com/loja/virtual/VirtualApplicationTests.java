package com.loja.virtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loja.virtual.controller.AcessoController;
import com.loja.virtual.model.Acesso;

@SpringBootTest(classes = VirtualApplication.class )
 public class VirtualApplicationTests {
   
	//@Autowired
	//private AcessoRepository acessoRepository;
	
	@Autowired
	private AcessoController acessoController;
	
	//@Autowired
	//private AcessoService acessoService;
	
	@Test
	public void testCadastroAcesso() {
		
		Acesso acesso = new Acesso();
		
		acesso.setDescrecao("ROLE_ADMIN");
		
		acessoController.salvarAcesso(acesso);
	}

}
