package br.com.fantonel.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "pedidoprodutos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PedidoProdutos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Min(value = 1, message = "Informe a quantidade do produto!")
	@Column(nullable = false)
	private BigDecimal qtde;
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	@Column(name = "valorfinal", nullable = false)
	private BigDecimal valorFinal = BigDecimal.ZERO;
	
	@ManyToOne(targetEntity = Pedido.class)
	@JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "pedidoproduto_fk01", value = ConstraintMode.CONSTRAINT))
	private Pedido pedido;

	@ManyToOne(targetEntity = Produto.class)
	@JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "pedidoproduto_fk02", value = ConstraintMode.CONSTRAINT))
	private Produto produto;
	
	public PedidoProdutos(){}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public BigDecimal getQtde() {
		return qtde;
	}

	public void setQtde(BigDecimal qtde) {
		this.qtde = qtde;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Override
	public String toString() {
		return "PedidoProdutos [id=" + id + "]";
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
		PedidoProdutos other = (PedidoProdutos) obj;
		return Objects.equals(id, other.id);
	}
}