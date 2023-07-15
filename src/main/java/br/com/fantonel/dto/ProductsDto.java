package br.com.fantonel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductsDto {
	@JsonProperty(value = "id")
	private String id;
	
	@JsonProperty(value = "quantity")
	private String quantity;
	
	public ProductsDto() {}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}