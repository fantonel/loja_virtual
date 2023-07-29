package br.com.fantonel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pessoajuridica")
public class PessoaJuridica implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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
	
	@Column(name = "telefoneprincipal", length = 11)
	private String telefonePrincipal;
	
	@Column(name = "excluida", nullable = false)
	private boolean excluida = false;
	
	@OneToMany(mappedBy = "pessoaJuridica", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Endereco> enderecos = new ArrayList<>();
	
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

	public String getTelefonePrincipal() {
		return telefonePrincipal;
	}

	public void setTelefonePrincipal(String telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	public boolean isExcluida() {
		return excluida;
	}

	public void setExcluida(boolean excluida) {
		this.excluida = excluida;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public Endereco getEndereco(String tipoEndereco) {
		if (enderecos != null && !enderecos.isEmpty()) {
			for (Endereco endereco : enderecos) {
				if (endereco.getTipoEndereco().getDescricao().toLowerCase().equals(tipoEndereco.toLowerCase()))
					return endereco;
			}			
		}
		return null;
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