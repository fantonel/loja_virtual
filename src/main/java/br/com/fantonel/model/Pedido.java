package br.com.fantonel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Column(nullable = false)
	private BigDecimal desconto;
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	@Column(name = "valorcomdescto", nullable = false)
	private BigDecimal valorComDescto;
	
	@Column(nullable = false)
	private BigDecimal frete;
	
	@Column(name = "valorcomfrete", nullable = false)
	private BigDecimal valorComFrete;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datapedido", nullable = false)
	private Date dataPedido;
	
	@Column(name = "codigorastreio")
	private String codigoRastreio;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "cupomdesconto_id", referencedColumnName = "id")
	private CupomDesconto cupomDesconto;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "pessoafisica_id", referencedColumnName = "id")
	private PessoaFisica pessoaFisica;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "pessoajuridica_id", referencedColumnName = "id")
	private PessoaJuridica pessaJuridica;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "enderecocobranca_id", referencedColumnName = "id")
	private Endereco enderecoCobranca;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "enderecoentrega_id", referencedColumnName = "id")
	private Endereco enderecoEntrega;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "transportadora_id", referencedColumnName = "id")
	private PessoaJuridica transportadora;
	
	@OneToMany(mappedBy = "pedido", orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<PedidoRastreio> pedidoRastreios;

	public Pedido() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getFrete() {
		return frete;
	}

	public void setFrete(BigDecimal frete) {
		this.frete = frete;
	}

	public BigDecimal getValorComDescto() {
		return valorComDescto;
	}

	public void setValorComDescto(BigDecimal valorComDescto) {
		this.valorComDescto = valorComDescto;
	}

	public BigDecimal getValorComFrete() {
		return valorComFrete;
	}

	public void setValorComFrete(BigDecimal valorComFrete) {
		this.valorComFrete = valorComFrete;
	}

	public String getCodigoRastreio() {
		return codigoRastreio;
	}

	public void setCodigoRastreio(String codigoRastreio) {
		this.codigoRastreio = codigoRastreio;
	}

	public CupomDesconto getCupomDesconto() {
		return cupomDesconto;
	}

	public void setCupomDesconto(CupomDesconto cupomDesconto) {
		this.cupomDesconto = cupomDesconto;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridica getPessaJuridica() {
		return pessaJuridica;
	}

	public void setPessaJuridica(PessoaJuridica pessaJuridica) {
		this.pessaJuridica = pessaJuridica;
	}

	public Endereco getEnderecoCobranca() {
		return enderecoCobranca;
	}

	public void setEnderecoCobranca(Endereco enderecoCobranca) {
		this.enderecoCobranca = enderecoCobranca;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public PessoaJuridica getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(PessoaJuridica transportadora) {
		this.transportadora = transportadora;
	}

	public List<PedidoRastreio> getPedidoRastreios() {
		return pedidoRastreios;
	}

	public void setPedidoRastreios(List<PedidoRastreio> pedidoRastreios) {
		this.pedidoRastreios = pedidoRastreios;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + "]";
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}
}