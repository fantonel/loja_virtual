package br.com.fantonel.model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "produtoconfiguracao")
public class ProdutoConfiguracao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Column(name = "cor", nullable = false)
	private String cor;
	
	@Size(min = 0, message = "A altura não pode ser menor que 0 (zero)")
	@Column(name = "altura", nullable = false)
	private Double altura;
	
	@Size(min = 0, message = "A largura não pode ser menor que 0 (zero)")
	@Column(name = "largura", nullable = false)
	private Double largura;
	
	@Size(min = 0, message = "A profundidade não pode ser menor que 0 (zero)")
	@Column(name = "profundidade", nullable = false)
	private Double profundidade;
	
	@Size(min = 0, message = "O peso não pode ser menor que 0 (zero)")
	@Column(name = "peso", nullable = false)
	private Double peso;
	
	@Size(min = 0, message = "O estoquue atual não pode ser inferior a 0 (zero)")
	@Column(name = "estoqueatual", nullable = false)
	private Integer estoqueAtual = 0;
	
	@Size(min = 0, message = "O estoque mínimo não pode ser inferior a 0 (zero)")
	@Column(name = "estoqueminimo", nullable = false)
	private Integer estoqueMinimo = 0;
	
	@Column(name = "numerovisualizacoes", nullable = false)
	private Integer numeroVisualizacoes = 0;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Produto.class)
	@JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(name = "produtoconfiguracao_fk01", value = ConstraintMode.CONSTRAINT))
	private Produto produto;
	
	@ManyToOne(targetEntity = UnidadeMedida.class)
	@JoinColumn(name = "unidademedida_id", nullable = false, foreignKey = @ForeignKey(name = "produtoconfiguracao_fk02", value = ConstraintMode.CONSTRAINT))
	private UnidadeMedida unidadeMedida;

	public ProdutoConfiguracao() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getLargura() {
		return largura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	public Double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Integer getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(Integer estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public Integer getNumeroVisualizacoes() {
		return numeroVisualizacoes;
	}

	public void setNumeroVisualizacoes(Integer numeroVisualizacoes) {
		this.numeroVisualizacoes = numeroVisualizacoes;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	
	

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ProdutoConfiguracao [id=" + id + "]";
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
		ProdutoConfiguracao other = (ProdutoConfiguracao) obj;
		return Objects.equals(id, other.id);
	}
}