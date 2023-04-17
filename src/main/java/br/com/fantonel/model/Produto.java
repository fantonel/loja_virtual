package br.com.fantonel.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Column(name = "nome")
	private String nome;
	
	@NotBlank(message = "Informe a descrição do produto!")
	@Column(name = "descricao", nullable = false, length = 2000)
	private String descricao;
	
	@Column(name = "numerovisualizacoes", nullable = false)
	private Double numeroVisualizacoes;
	
	@Column(name = "linkyoutube", nullable = false)
	private String linkYoutube;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "marca_id", referencedColumnName = "id")
	private Marca marca;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "produtocategoria", uniqueConstraints = @UniqueConstraint(columnNames = {"produto_id", "categoria_id"},name = "produtocategoria_pk"),
			   joinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id", table = "produto", unique = false, foreignKey = @ForeignKey(name = "produtocategoria_fk01", value = ConstraintMode.CONSTRAINT)),
			   inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id", table = "categoria", unique = false, foreignKey = @ForeignKey(name = "produtocategoria_fk02", value = ConstraintMode.CONSTRAINT))
	)
	private List<Categoria> produtoCategorias;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<ProdutoConfiguracao> produtoConfiguracoes;
	
	@OneToMany(mappedBy = "produto", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProdutoImagem> imagens;

	public Produto() {
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getNumeroVisualizacoes() {
		return numeroVisualizacoes;
	}

	public void setNumeroVisualizacoes(Double numeroVisualizacoes) {
		this.numeroVisualizacoes = numeroVisualizacoes;
	}

	public String getLinkYoutube() {
		return linkYoutube;
	}

	public void setLinkYoutube(String linkYoutube) {
		this.linkYoutube = linkYoutube;
	}
	
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Categoria> getProdutoCategorias() {
		return produtoCategorias;
	}

	public void setProdutoCategorias(List<Categoria> produtoCategorias) {
		this.produtoCategorias = produtoCategorias;
	}	

	public List<ProdutoConfiguracao> getProdutoConfiguracoes() {
		return produtoConfiguracoes;
	}

	public void setProdutoConfiguracoes(List<ProdutoConfiguracao> produtoConfiguracoes) {
		this.produtoConfiguracoes = produtoConfiguracoes;
	}

	public List<ProdutoImagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<ProdutoImagem> imagens) {
		this.imagens = imagens;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + "]";
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
}