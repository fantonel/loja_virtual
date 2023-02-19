package br.com.fantonel.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@javax.persistence.Entity
@Table(name = "acesso")
public class Acesso implements GrantedAuthority{
	private static final long serialVersionUID = 1L;
	
	@Id
	private UUID id;
	
	@Column(nullable = false, unique = true, length = 30)
	private String descricao;  /*Acesso ex: ROLE_ADMIN ou ROLE_SECRETARIO*/
	
	@JsonIgnore
	@Override
	public String getAuthority() {
		return this.descricao;
	}
}