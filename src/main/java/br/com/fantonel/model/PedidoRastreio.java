package br.com.fantonel.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "pedidorastreio")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PedidoRastreio implements Serializable {
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahora", nullable = false)
	private Date dataHora;
	
	@NotBlank(message = "Informe o status do rastreio")
	@Column(nullable = false)
	private String status;
	
	@NotBlank(message = "Informe a cidade")
	@Column(nullable = false)
	private String cidade;
	
	@NotBlank(message = "Informe o estado do país")
	@Column(nullable = false)
	private String uf;
	
	@Column(nullable = false)
	private String local;
	
	@ManyToOne(targetEntity = Pedido.class)
	@JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(name = "pedidorastreio_fk01",  value = ConstraintMode.CONSTRAINT))
	private Pedido pedido;

	public PedidoRastreio() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		return "PedidoRastreio [id=" + id + "]";
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
		PedidoRastreio other = (PedidoRastreio) obj;
		return Objects.equals(id, other.id);
	}
}