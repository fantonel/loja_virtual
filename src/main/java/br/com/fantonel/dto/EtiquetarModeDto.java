package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EtiquetarModeDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "orders")
	String[] orders;

	public EtiquetarModeDto() {
	}
	
	public EtiquetarModeDto(String[] orders) {
		this.orders = orders;
	}

	public String[] getOrders() {
		return orders;
	}

	public void setOrders(String[] orders) {
		this.orders = orders;
	}
}