package br.com.fantonel.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable, UserDetails {	
	private static final long serialVersionUID = 1L;
	
	@Id
	private UUID id;
	
	@Column(name = "login", nullable = false, length = 120, unique = true)
	private String login;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "dataatualizacao", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAtualizacao;
	
	@ManyToOne(targetEntity = PessoaFisica.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoa_id", nullable = true, foreignKey = @ForeignKey(name = "usuario_fk01", value = ConstraintMode.CONSTRAINT))
	private PessoaFisica pessoaFisica;
	
	@ManyToOne(targetEntity = PessoaJuridica.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "empresa_id", nullable = true, foreignKey = @ForeignKey(name = "usuario_fk02", value = ConstraintMode.CONSTRAINT))
	private PessoaJuridica pessoaJuridica;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarioacessos", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "acesso_id"},name = "usuarioacessos_pk"),
			   joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", table = "usuario", unique = false, foreignKey = @ForeignKey(name = "usuarioacessos_fk01", value = ConstraintMode.CONSTRAINT)),
			   inverseJoinColumns = @JoinColumn(name = "acesso_id", referencedColumnName = "id", table = "acesso", unique = false, foreignKey = @ForeignKey(name = "usuarioacessos_fk02", value = ConstraintMode.CONSTRAINT))
	)
	private List<Acesso> acessos;
	
	public Usuario() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}	

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public List<Acesso> getAcessos() {
		return acessos;
	}

	public void setAcessos(List<Acesso> acessos) {
		this.acessos = acessos;
	}

	//Reference as ROLES (ROLE_xxx, ROLE_yyy, ...)
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.acessos;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}