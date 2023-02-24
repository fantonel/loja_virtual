package br.com.fantonel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "pessoafisica")
public class PessoaFisica implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@NotBlank(message = "Informe o seu nome")
	@Column(nullable = false, length = 120)
	private String nome;
	
	@Column(name = "datanascto", nullable = false)
	private Date dataNascto;
	
	@NotBlank(message = "Informe o seu cpf")
	@CPF(message = "Informe um cpf válido")
	@Column(name = "cpf", length = 11)
	private String cpf;
	
	@Column(name = "rg", length = 9)
	private String rg;
	
	@NotBlank(message = "Informe o seu email")
	@Email(message = "Informe um email válido")
	@Column(name = "email", length = 120)
	private String email;
		
	@Column(name = "excluida", nullable = false)
	private boolean excluida;
	
	public PessoaFisica() {}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascto() {
		return dataNascto;
	}

	public void setDataNascto(Date dataNascto) {
		this.dataNascto = dataNascto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isExcluida() {
		return excluida;
	}

	public void setExcluida(boolean excluida) {
		this.excluida = excluida;
	}

	@Override
	public String toString() {
		return "Entidade [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaFisica other = (PessoaFisica) obj;
		return Objects.equals(id, other.id);
	}
}