package br.com.fantonel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "pessoafisica")
public class PessoaFisica implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private UUID id;
	
	@NotBlank(message = "Informe o seu nome")
	@Column(nullable = false, length = 120)
	private String nome;
	
	@NotEmpty(message = "Informe a sua data de nascimento")
	@Column(name = "datanascto", nullable = false)
	private Date dataNascto;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dthrcadastro", nullable = false)
	private Date dtHrCadastro;	
	
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

	public Date getDtHrCadastro() {
		return dtHrCadastro;
	}

	public void setDtHrCadastro(Date dtHrCadastro) {
		this.dtHrCadastro = dtHrCadastro;
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