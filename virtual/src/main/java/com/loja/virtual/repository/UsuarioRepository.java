package com.loja.virtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.loja.virtual.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	@Query(value = "select u from Usuario u where u.login = ?1")
	Usuario findUserByLogin(String login);
	
	@Query(value = "select u from Usuario u where u.datAtualSenha <= current_date - 90")
	List<Usuario> usuarioSenhaVencida();

    
	@Query(value = "select u from Usuario u where u.pessoa.id = ?1 or u.login =?2")
	Usuario findUserByPessoa(Long id, String email);
    
    @Query(value = "select constraint_name from information_schema.constraint_column_usage where table_name= 'usuarios_acesso' and column_name = 'acesso_id'\r\n"
    		+ "and constraint_name <> 'unique_acesso_user';",nativeQuery = true)
	String consultaConstraintAcesso();
    
    @javax.transaction.Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into usuarios_acesso(usuario_id, acesso_id) values (?1, (select id from acesso where descricao = 'ROLE_USER'))")
	void insereAcessoUserPj(long id);



}
