package br.com.fantonel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "melhorenvio")
public class MelhorEnvio implements Serializable {
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Column(name = "melhorenvio_freteservice")
	private Integer melhorEnvioFreteService;
	
	@Column(name = "melhorenvio_freteagency")
	private Integer melhorEnvioFreteAgency;
	
	@Column(name = "melhorenvio_freteid")
	private UUID melhorEnvioFreteId;
	
	@Column(name = "melhorenvio_fretename")
	private String melhorEnvioFreteName;
	
	@Column(name = "melhorenvio_freteprice")
	private BigDecimal melhorEnvioFretePrice;
	
	@Column(name = "melhorenvio_fretecustomprice")
	private BigDecimal melhorEnvioFreteCustomPrice;
	
	@Column(name = "melhorenvio_fretedeliverytime")
	private BigDecimal melhorEnvioFreteDeliveryTime;
	
	@Column(name = "melhorenvio_fretecustomdeliverytime")
	private BigDecimal melhorEnvioFreteCustomDeliveryTime;
	
	@Column(name = "melhorenvio_inserirfreteid")
	private String melhorEnvioInserirFreteId;
	
	@Column(name = "melhorenvio_comprarfreteid")
	private String melhorEnvioComprarFreteId;
	
	@Column(name = "melhorenvio_urletiqueta")
	private String melhorEnvioUrletiqueta;
	
	@Column(name = "melhorenvio_codigorastreio")
	private String melhorEnvioCodigoRastreio;	
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "pedido_id", referencedColumnName = "id")
	private Pedido pedido;
	
	@ManyToOne(targetEntity = Transportadora.class)
	@JoinColumn(name = "transportadora_id", nullable = true, foreignKey = @ForeignKey(name = "melhorenvio_fk02", value = ConstraintMode.CONSTRAINT))
	private Transportadora transportadora;

	public MelhorEnvio() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getMelhorEnvioFreteService() {
		return melhorEnvioFreteService;
	}

	public void setMelhorEnvioFreteService(Integer melhorEnvioFreteService) {
		this.melhorEnvioFreteService = melhorEnvioFreteService;
	}

	public Integer getMelhorEnvioFreteAgency() {
		return melhorEnvioFreteAgency;
	}

	public void setMelhorEnvioFreteAgency(Integer melhorEnvioFreteAgency) {
		this.melhorEnvioFreteAgency = melhorEnvioFreteAgency;
	}

	public UUID getMelhorEnvioFreteId() {
		return melhorEnvioFreteId;
	}

	public void setMelhorEnvioFreteId(UUID melhorEnvioFreteId) {
		this.melhorEnvioFreteId = melhorEnvioFreteId;
	}

	public String getMelhorEnvioFreteName() {
		return melhorEnvioFreteName;
	}

	public void setMelhorEnvioFreteName(String melhorEnvioFreteName) {
		this.melhorEnvioFreteName = melhorEnvioFreteName;
	}

	public BigDecimal getMelhorEnvioFretePrice() {
		return melhorEnvioFretePrice;
	}

	public void setMelhorEnvioFretePrice(BigDecimal melhorEnvioFretePrice) {
		this.melhorEnvioFretePrice = melhorEnvioFretePrice;
	}

	public BigDecimal getMelhorEnvioFreteCustomPrice() {
		return melhorEnvioFreteCustomPrice;
	}

	public void setMelhorEnvioFreteCustomPrice(BigDecimal melhorEnvioFreteCustomPrice) {
		this.melhorEnvioFreteCustomPrice = melhorEnvioFreteCustomPrice;
	}

	public BigDecimal getMelhorEnvioFreteDeliveryTime() {
		return melhorEnvioFreteDeliveryTime;
	}

	public void setMelhorEnvioFreteDeliveryTime(BigDecimal melhorEnvioFreteDeliveryTime) {
		this.melhorEnvioFreteDeliveryTime = melhorEnvioFreteDeliveryTime;
	}

	public BigDecimal getMelhorEnvioFreteCustomDeliveryTime() {
		return melhorEnvioFreteCustomDeliveryTime;
	}

	public void setMelhorEnvioFreteCustomDeliveryTime(BigDecimal melhorEnvioFreteCustomDeliveryTime) {
		this.melhorEnvioFreteCustomDeliveryTime = melhorEnvioFreteCustomDeliveryTime;
	}

	public String getMelhorEnvioInserirFreteId() {
		return melhorEnvioInserirFreteId;
	}

	public void setMelhorEnvioInserirFreteId(String melhorEnvioInserirFreteId) {
		this.melhorEnvioInserirFreteId = melhorEnvioInserirFreteId;
	}

	public String getMelhorEnvioComprarFreteId() {
		return melhorEnvioComprarFreteId;
	}

	public void setMelhorEnvioComprarFreteId(String melhorEnvioComprarFreteId) {
		this.melhorEnvioComprarFreteId = melhorEnvioComprarFreteId;
	}

	public String getMelhorEnvioUrletiqueta() {
		return melhorEnvioUrletiqueta;
	}

	public void setMelhorEnvioUrletiqueta(String melhorEnvioUrletiqueta) {
		this.melhorEnvioUrletiqueta = melhorEnvioUrletiqueta;
	}

	public String getMelhorEnvioCodigoRastreio() {
		return melhorEnvioCodigoRastreio;
	}

	public void setMelhorEnvioCodigoRastreio(String melhorEnvioCodigoRastreio) {
		this.melhorEnvioCodigoRastreio = melhorEnvioCodigoRastreio;
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

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MelhorEnvio other = (MelhorEnvio) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "MelhorEnvio [id=" + id + "]";
	}
}