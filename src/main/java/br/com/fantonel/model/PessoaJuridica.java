package br.com.fantonel.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "pessoajuridica")
public class PessoaJuridica implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private UUID id;
	
	@NotBlank(message = "Informe o CNPJ")
	@Column(nullable = false, length = 14)
	private String cnpj;
	
	@NotBlank(message = "Informe a inscrição estadual")
	@Column(nullable = false, length = 20)
	private String ie;
	
	@Column(length = 20)
	private String im;
	
	@NotBlank(message = "Informe a razão social")
	@Column(name = "razaosocial", nullable = false, length = 120)
	private String razaoSocial;
	
	@NotBlank(message = "Informe o nome fantasia")
	@Column(name = "nomefantasia", nullable = false, length = 120)
	private String nomeFantasia;
	
	@NotBlank(message = "Informe o email principal")
	@Column(name = "emailprincipal", nullable = false, length = 120)
	private String emailPrincipal;
	
	public PessoaJuridica() {}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	public String getEmailPrincipal() {
		return emailPrincipal;
	}

	public void setEmailPrincipal(String emailPrincipal) {
		this.emailPrincipal = emailPrincipal;
	}

	@Override
	public String toString() {
		return "PessoaJuridica [id=" + id + "]";
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
		PessoaJuridica other = (PessoaJuridica) obj;
		return Objects.equals(id, other.id);
	}
}