package br.com.fantonel.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsereFreteProductsDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "quantity")
	private BigDecimal quantity;
	
	@JsonProperty(value = "unitary_value")
	private BigDecimal unitaryValue;
	
	@JsonProperty(value = "weight")
	private int weight;

	public InsereFreteProductsDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitaryValue() {
		return unitaryValue;
	}

	public void setUnitaryValue(BigDecimal unitaryValue) {
		this.unitaryValue = unitaryValue;
	}	

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "InsereFreteProductsDto [name=" + name + ", quantity=" + quantity + ", unitaryValue=" + unitaryValue
				+ "]";
	}
}