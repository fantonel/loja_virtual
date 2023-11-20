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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "boleto")
public class Boleto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Column(name = "chargeICartao")
	private String chargeICartao = "";
	
	/*
	 * Mostra a de checkout com os boleto, pix e cartão pagos ou vencidos
	 */
	@Column(name = "checkouturl")
	private String checkOutUrl = "";
	
	/* Código de controle do boleto */
	@Column(name = "code")
	private String code = "";
	
	@Column(name = "datavencimento")
	private String dataVencimento = "";
	
	/*Id controle do boleto para poder cancelar pela api*/
	@Column(name = "idchrboleto")
	private String idChrBoleto = "";
	
	@Column(name = "idpix")
	private String IdPix = "";
	
	@Column(name = "imageinbase64")
	private String imageInBase64 = "";
	
	/* Link da parcela do boleto */
	@Column(name = "installmentLink")
	private String installmentLink = "";	

	/* Imprime o boleto completo com todas as parcelas */
	@Column(name = "link")
	private String link;
	
	@Column(name = "payloadinbase64")
	private String payloadInBase64 = "";

	@Column(name = "quitado")
	private boolean quitado = false;
	
	@Column(name = "recorrencia")
	private Integer recorrencia = 0;

	@Column(name = "valor")
	private BigDecimal valor = BigDecimal.ZERO;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "boleto_fk001"))
	private Pedido pedido;
	
	@ManyToOne(targetEntity = Empresa.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "boleto_fk002"))
	private Empresa empresa;
	
	public Boleto() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getChargeICartao() {
		return chargeICartao;
	}

	public void setChargeICartao(String chargeICartao) {
		this.chargeICartao = chargeICartao;
	}

	public String getCheckOutUrl() {
		return checkOutUrl;
	}

	public void setCheckOutUrl(String checkOutUrl) {
		this.checkOutUrl = checkOutUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getIdChrBoleto() {
		return idChrBoleto;
	}

	public void setIdChrBoleto(String idChrBoleto) {
		this.idChrBoleto = idChrBoleto;
	}

	public String getIdPix() {
		return IdPix;
	}

	public void setIdPix(String idPix) {
		IdPix = idPix;
	}

	public String getImageInBase64() {
		return imageInBase64;
	}

	public void setImageInBase64(String imageInBase64) {
		this.imageInBase64 = imageInBase64;
	}

	public String getInstallmentLink() {
		return installmentLink;
	}

	public void setInstallmentLink(String installmentLink) {
		this.installmentLink = installmentLink;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPayloadInBase64() {
		return payloadInBase64;
	}

	public void setPayloadInBase64(String payloadInBase64) {
		this.payloadInBase64 = payloadInBase64;
	}

	public boolean isQuitado() {
		return quitado;
	}

	public void setQuitado(boolean quitado) {
		this.quitado = quitado;
	}

	public Integer getRecorrencia() {
		return recorrencia;
	}

	public void setRecorrencia(Integer recorrencia) {
		this.recorrencia = recorrencia;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Boleto [id=" + id + "]";
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
		Boleto other = (Boleto) obj;
		return Objects.equals(id, other.id);
	}
}