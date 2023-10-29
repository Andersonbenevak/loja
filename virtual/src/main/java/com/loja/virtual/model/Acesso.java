package com.loja.virtual.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

/**/
@Entity
@Table(name = "Acesso")
@SequenceGenerator(name = "seq_acesso", sequenceName = "seq_acesso", initialValue = 1, allocationSize = 1)

public class Acesso implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acesso")
	private long id;
    
	@Column(nullable = false)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Acesso acesso = (Acesso) o;
		return id == acesso.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
    @JsonIgnore
	@Override
	public String getAuthority() {
		return null;
	}
}
