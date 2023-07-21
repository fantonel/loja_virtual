package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompraFreteOrdersDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "order_id")
	private String orderId;

	public CompraFreteOrdersDto() {
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "CompraFreteOrdersDto [orderId=" + orderId + "]";
	}
}