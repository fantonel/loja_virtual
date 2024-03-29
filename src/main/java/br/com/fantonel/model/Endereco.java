package br.com.fantonel.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fantonel.enums.TipoEndereco;
import br.com.fantonel.enums.TipoLogradouro;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@NotBlank(message = "Informe o cep")
	@Column(name = "cep", nullable = false, length=10)
	private String cep;
	
	@Column(name = "tipologradouro",nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoLogradouro tipoLogradouro;	
	
	@NotBlank(message = "Informe o logradouro")
	@Column(name = "logradouro", nullable = false)
	private String logradouro;
	
	@NotBlank(message = "Informe o número")
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@Column(name = "complemento", nullable = false)
	private String complemento;
	
	@NotBlank(message = "Informe o bairro")
	@Column(name = "bairro", nullable = false)
	private String bairro;
	
	@NotBlank(message = "Informe a cidade")
	@Column(name = "localidade", nullable = false)
	private String localidade;
	
	@NotBlank(message = "Informe o estado")
	@Column(name = "uf", nullable = false)
	private String uf;
	
	@Column(name = "ibge", nullable = false)
	private String ibge;
	
	@Column(name = "gia", nullable = false)
	private String gia;
	
	@Column(name = "ddd", nullable = false)
	private String ddd;
	
	@Column(name = "siafi", nullable = false)
	private String siafi;
	
	@Column(name = "tipoendereco", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipoEndereco;
	
	@Column(nullable = false)
	private boolean ativo = true;
	
	@JsonIgnore
	@ManyToOne(targetEntity = PessoaFisica.class)
	@JoinColumn(name = "pessoa_id", nullable = true, foreignKey = @ForeignKey(name = "endereco_fk01", value = ConstraintMode.CONSTRAINT))
	private PessoaFisica pessoaFisica;
	
	@JsonIgnore
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = true, foreignKey = @ForeignKey(name = "endereco_fk02", value = ConstraintMode.CONSTRAINT))
	private PessoaJuridica pessoaJuridica;

	public Endereco() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getSiafi() {
		return siafi;
	}

	public void setSiafi(String siafi) {
		this.siafi = siafi;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}	

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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

	@Override
	public String toString() {
		return "Endereco [id=" + id + "]";
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
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}	
}