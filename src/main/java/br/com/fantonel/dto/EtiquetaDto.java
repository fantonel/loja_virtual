package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EtiquetaDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "orders")
	String[] orders;

	public EtiquetaDto() {
	}
	
	public EtiquetaDto(String[] orders) {
		this.orders = orders;
	}

	public String[] getOrders() {
		return orders;
	}

	public void setOrders(String[] orders) {
		this.orders = orders;
	}
}