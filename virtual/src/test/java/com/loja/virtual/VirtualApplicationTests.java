package com.loja.virtual;

import com.loja.virtual.repository.AcessoRepository;
import com.loja.virtual.service.AcessoService;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loja.virtual.controller.AcessoController;
import com.loja.virtual.model.Acesso;

@SpringBootTest(classes = VirtualApplication.class )
 public class VirtualApplicationTests extends TestCase {
   
	@Autowired
	private AcessoRepository acessoRepository;
	@Autowired(required = false)
	private AcessoController acessoController;
	
	@Autowired
	private AcessoService acessoService;

	@Test
	public void testCadastroAcesso() {


		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_ADMIN");

       acesso = acessoController.salvarAcesso(acesso).getBody();

	   assertEquals(true, acesso.getId() > 0);

		assertEquals("ROLE_ADMIN", acesso.getDescricao());
	}

}
