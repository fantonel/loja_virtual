package br.com.fantonel.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MelhorEnvioResponseDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
	private String id;
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "error")
	private String error;
	
	@JsonProperty(value = "price")
	private String price;
	
	@JsonProperty(value = "custom_price")
	private String customPrice;
	
	@JsonProperty(value = "discount")
	private String discount;
	
	@JsonProperty(value = "currency")
	private String currency;
	
	@JsonProperty(value = "delivery_time")
	private String deliveryTime;
	
	@JsonProperty(value = "delivery_range")
	private DeliveryyRangeDto deliveryRange;
	
	@JsonProperty(value = "custom_delivery_time")
	private String cutomDeliveryTime;
	
	@JsonProperty(value = "custom_delivery_range")
	private CustomDeliveryRangeDto customDeliveryRange;
	
	@JsonProperty(value = "packages")
	private PackagesDto[] packagesDto;
	
	@JsonProperty(value = "additional_services")
	private AditionalServicesDto aditionalServicesDto;
	
	@JsonProperty(value = "company")
	private CompanyDto companyDto;

	public MelhorEnvioResponseDto() {
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCustomPrice() {
		return customPrice;
	}

	public void setCustomPrice(String customPrice) {
		this.customPrice = customPrice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public DeliveryyRangeDto getDeliveryRange() {
		return deliveryRange;
	}

	public void setDeliveryRange(DeliveryyRangeDto deliveryRange) {
		this.deliveryRange = deliveryRange;
	}

	public String getCutomDeliveryTime() {
		return cutomDeliveryTime;
	}


	public void setCutomDeliveryTime(String cutomDeliveryTime) {
		this.cutomDeliveryTime = cutomDeliveryTime;
	}


	public CustomDeliveryRangeDto getCustomDeliveryRange() {
		return customDeliveryRange;
	}

	public void setCustomDeliveryRange(CustomDeliveryRangeDto customDeliveryRange) {
		this.customDeliveryRange = customDeliveryRange;
	}

	public PackagesDto[] getPackagesDto() {
		return packagesDto;
	}


	public void setPackagesDto(PackagesDto[] packagesDto) {
		this.packagesDto = packagesDto;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AditionalServicesDto getAditionalServicesDto() {
		return aditionalServicesDto;
	}

	public void setAditionalServicesDto(AditionalServicesDto aditionalServicesDto) {
		this.aditionalServicesDto = aditionalServicesDto;
	}

	public CompanyDto getCompanyDto() {
		return companyDto;
	}

	public void setCompanyDto(CompanyDto companyDto) {
		this.companyDto = companyDto;
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
		MelhorEnvioResponseDto other = (MelhorEnvioResponseDto) obj;
		return Objects.equals(id, other.id);
	}
}