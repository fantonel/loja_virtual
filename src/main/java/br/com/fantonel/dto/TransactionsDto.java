package br.com.fantonel.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionsDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
	private String id;
	
	@JsonProperty(value = "protocol")
	private String protocol;
	
	@JsonProperty(value = "value")
	private BigDecimal value;
	
	@JsonProperty(value = "type")
	private String type;
	
	@JsonProperty(value = "status")
	private String status;
	
	@JsonProperty(value = "description")
	private String description;
	
	@JsonProperty(value = "authorized_at")
	private String authorizedAt;
	
	@JsonProperty(value = "unauthorized_at")
	private String unauthorizedAt;
	
	@JsonProperty(value = "reserved_at")
	private String reservedAt;
	
	@JsonProperty(value = "canceled_at")
	private String canceledAt;
	
	@JsonProperty(value = "created_at")
	private String createdAt;
	
	@JsonProperty(value = "description_internal")
	private String descriptionInternal;
	
	@JsonProperty(value = "reason")	
	private ReasonDto reason;

	public TransactionsDto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}	

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthorizedAt() {
		return authorizedAt;
	}

	public void setAuthorizedAt(String authorizedAt) {
		this.authorizedAt = authorizedAt;
	}

	public String getUnauthorizedAt() {
		return unauthorizedAt;
	}

	public void setUnauthorizedAt(String unauthorizedAt) {
		this.unauthorizedAt = unauthorizedAt;
	}

	public String getReservedAt() {
		return reservedAt;
	}

	public void setReservedAt(String reservedAt) {
		this.reservedAt = reservedAt;
	}

	public String getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(String canceledAt) {
		this.canceledAt = canceledAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescriptionInternal() {
		return descriptionInternal;
	}

	public void setDescriptionInternal(String descriptionInternal) {
		this.descriptionInternal = descriptionInternal;
	}

	public ReasonDto getReason() {
		return reason;
	}

	public void setReason(ReasonDto reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "TransactionsDto [id=" + id + "]";
	}
}