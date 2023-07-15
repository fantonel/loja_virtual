package br.com.fantonel.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PackagesDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "price")
	private String price;
	
	@JsonProperty(value = "discount")
	private String discount;
	
	@JsonProperty(value = "format")
	private String format;
	
	@JsonProperty(value = "weight")
	private String weight;		
	
	@JsonProperty(value = "insurance_value")
	private String insuranceValue;
	
	@JsonProperty(value = "dimensions")
	private DimensionsDto dimensions;
	
	@JsonProperty(value = "products")
	private List<ProductsDto> products;
	
	public PackagesDto() {}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getInsuranceValue() {
		return insuranceValue;
	}

	public void setInsuranceValue(String insuranceValue) {
		this.insuranceValue = insuranceValue;
	}

	public DimensionsDto getDimensions() {
		return dimensions;
	}

	public void setDimensions(DimensionsDto dimensions) {
		this.dimensions = dimensions;
	}

	public List<ProductsDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductsDto> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "PackagesDto [price=" + price + ", discount=" + discount + ", format=" + format + ", weight=" + weight
				+ ", insuranceValue=" + insuranceValue + ", dimensions=" + dimensions + ", products=" + products + "]";
	}
}