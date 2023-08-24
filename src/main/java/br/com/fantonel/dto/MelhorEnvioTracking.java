package br.com.fantonel.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MelhorEnvioTracking implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
	private String id;
	@JsonProperty(value = "protocol")
	private String protocol;
	@JsonProperty(value = "status")
	private String status;
	@JsonProperty(value = "tracking")
	private String tracking;
	@JsonProperty(value = "melhorenvio_tracking")
	private String melhorEnvioTracking;
	@JsonProperty(value = "created_at")
	private String createdAt;
	@JsonProperty(value = "paid_at")
	private String paidAt;
	@JsonProperty(value = "generated_at")
	private String generatedAt;
	@JsonProperty(value = "posted_at")
	private String postedAt;
	@JsonProperty(value = "delivered_at")
	private String deliveredAt;
	@JsonProperty(value = "canceled_at")
	private String canceledAt;
	@JsonProperty(value = "expired_at")
	private String expiredAt;

	public MelhorEnvioTracking() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTracking() {
		return tracking;
	}

	public void setTracking(String tracking) {
		this.tracking = tracking;
	}

	public String getMelhorEnvioTracking() {
		return melhorEnvioTracking;
	}

	public void setMelhorEnvioTracking(String melhorEnvioTracking) {
		this.melhorEnvioTracking = melhorEnvioTracking;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(String paidAt) {
		this.paidAt = paidAt;
	}

	public String getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(String generatedAt) {
		this.generatedAt = generatedAt;
	}

	public String getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(String postedAt) {
		this.postedAt = postedAt;
	}

	public String getDeliveredAt() {
		return deliveredAt;
	}

	public void setDeliveredAt(String deliveredAt) {
		this.deliveredAt = deliveredAt;
	}

	public String getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(String canceledAt) {
		this.canceledAt = canceledAt;
	}

	public String getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(String expiredAt) {
		this.expiredAt = expiredAt;
	}
	
	@Override
	public String toString() {
		return "MelhorEnvioXXX [id=" + id + "]";
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
		MelhorEnvioTracking other = (MelhorEnvioTracking) obj;
		return Objects.equals(id, other.id);
	}
}