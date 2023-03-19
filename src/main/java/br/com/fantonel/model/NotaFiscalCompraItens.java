package br.com.fantonel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "notafiscalcompraitens")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class NotaFiscalCompraItens implements Serializable {
	private static final long serialVersionUID = -8128908999803345356L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Min(value = 1, message = "A quantidade deve ser maior que 0 (zero)")
	@Column(nullable = false)
	private Integer quantidade;
	
	@Min(value = 0, message = "O valor de compra não pode ser menor que R$0.00")
	@Column(nullable = false)
	private BigDecimal valor;
	
	@Min(value = 0, message = "O desconto no item não pode ser menor que R$0.00")
	@Column(nullable = false)
	private BigDecimal desconto;
	
	@Min(value = 0, message = "O valor com desconto, não pode menor que R$0.00")
	@Column(nullable = false)
	private BigDecimal total;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahora", nullable = false)
	private Date dataHora;
	
	@ManyToOne(targetEntity = Produto.class)
	@JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "notafiscalcompraitens_fk01", value = ConstraintMode.CONSTRAINT))	
	private Produto produto;
	
	@ManyToOne(targetEntity = NotaFiscalCompra.class)
	@JoinColumn(name = "notafiscalcompra_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "notafiscalcompraitens_fk02", value = ConstraintMode.CONSTRAINT))
	private NotaFiscalCompra notaFiscalCompra;

	public NotaFiscalCompraItens() {
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public NotaFiscalCompra getNotaFiscalCompra() {
		return notaFiscalCompra;
	}

	public void setNotaFiscalCompra(NotaFiscalCompra notaFiscalCompra) {
		this.notaFiscalCompra = notaFiscalCompra;
	}

	@Override
	public String toString() {
		return "NotaFiscalCompraItens [id=" + id + "]";
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
		NotaFiscalCompraItens other = (NotaFiscalCompraItens) obj;
		return Objects.equals(id, other.id);
	}
}