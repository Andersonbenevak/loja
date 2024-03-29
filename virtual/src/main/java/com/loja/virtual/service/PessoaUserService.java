package com.loja.virtual.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.loja.virtual.model.PessoaJuridica;
import com.loja.virtual.model.Usuario;
import com.loja.virtual.repository.PessoaRepository;
import com.loja.virtual.repository.UsuarioRepository;

@Service
public class PessoaUserService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ServiceSendEmail serviceSendEmail;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica juridica) {

		for (int i = 0; i < juridica.getEnderecos().size(); i++) {
			juridica.getEnderecos().get(i).setPessoa(juridica);
			juridica.getEnderecos().get(i).setEmpresa(juridica);
		}

		juridica = pessoaRepository.save(juridica);

		Usuario usuarioPj = usuarioRepository.findUserByPessoa(juridica.getId(), juridica.getEmail());

		if (usuarioPj == null) {

			String constraint = usuarioRepository.consultaConstraintAcesso();
			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint + "; commit;");
			}

			usuarioPj = new Usuario();

			usuarioPj.setDatAtualSenha(Calendar.getInstance().getTime());
			usuarioPj.setEmpresa(juridica);
			usuarioPj.setPessoa(juridica);
			usuarioPj.setLogin(juridica.getEmail());

			String senha = "" + Calendar.getInstance().getTimeInMillis();
			String senhaCript = new BCryptPasswordEncoder().encode(senha);
			usuarioPj.setSenha(senhaCript);
			usuarioPj = usuarioRepository.save(usuarioPj);

			usuarioRepository.insereAcessoUserPj(usuarioPj.getId());

			StringBuilder menssagemHtml = new StringBuilder();

			menssagemHtml.append("<b>Segue seus dados de acesso do e-commerce, isso é um teste da aplicação</b><br/>");
			menssagemHtml.append("<b>Login: </b>" + juridica.getEmail() + "<br/>");
			menssagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>"); 
			menssagemHtml.append("Obrigado!");

			try {
				serviceSendEmail.EnviarEmailHtml("Acesso Gerado para Loja Virtual", menssagemHtml.toString(),
						juridica.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return juridica;
	}

}
