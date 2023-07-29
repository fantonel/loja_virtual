package br.com.fantonel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "notafiscalvenda")	
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class NotaFiscalVenda implements Serializable {
	private static final long serialVersionUID = -8128908999803345356L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Column(length = 2000)
	private String descricao;
	
	@NotBlank(message = "Informe o número da nota")
	@Column(nullable = false, length = 60)
	private String numero;
	
	@NotBlank(message = "Informe o número da nota")
	@Column(nullable = false, length = 15)
	private String serie;
	
	@Min(value = 0, message = "Valor não pode ser menor que R$0.00")
	@Column(nullable = false)
	private BigDecimal valor;
	
	@Min(value = 0, message = "Icms não pode ser menor que R$0.00")
	@Column(nullable = false)
	private BigDecimal icms;
	
	@Min(value = 0, message = "Desconto não ser menor que R$0.00")
	@Column(nullable = false)
	private BigDecimal desconto;
	
	@Min(value = 0, message = "Total não ser menor que R$0.00")
	@Column(nullable = false)
	private BigDecimal total;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datavenda", nullable = false)
	private Date dataVenda;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dataemissao", nullable = false)
	private Date dataEmissao;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoafisica_id", referencedColumnName = "id", nullable = true)
	private PessoaFisica pessoaFisica;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoajuridica_id", referencedColumnName = "id", nullable = true)
	private PessoaJuridica pessoaJuridica;	
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "pedido_id", referencedColumnName = "id")
	private Pedido pedido;
	
	public NotaFiscalVenda() {
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}



	public BigDecimal getIcms() {
		return icms;
	}

	public void setIcms(BigDecimal icms) {
		this.icms = icms;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		NotaFiscalVenda other = (NotaFiscalVenda) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "NotaFiscalCompra [id=" + id + "]";
	}	
}