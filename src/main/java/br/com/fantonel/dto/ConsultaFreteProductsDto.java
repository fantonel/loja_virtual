package br.com.fantonel.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ConsultaFreteProductsDto {
	
	private UUID id;
	private BigDecimal width;
	private BigDecimal height;
	private BigDecimal length;
	private BigDecimal weight;
	private BigDecimal insurance_value;
	private BigDecimal quantity;

	public ConsultaFreteProductsDto() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getInsurance_value() {
		return insurance_value;
	}

	public void setInsurance_value(BigDecimal insurance_value) {
		this.insurance_value = insurance_value;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}	
}