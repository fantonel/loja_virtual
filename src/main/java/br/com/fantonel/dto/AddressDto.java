package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "id")
    private Integer id;
	
	@JsonProperty(value = "label")
    private String label;
	
	@JsonProperty(value = "postal_code")
    private String postalCode;
	
	@JsonProperty(value = "address")
    private String address;
	
	@JsonProperty(value = "number")
    private String number;
	
	@JsonProperty(value = "complement")
    private String complement;
	
	@JsonProperty(value = "district")
    private String district;
	
	@JsonProperty(value = "latitude")
    private String latitude;
	
	@JsonProperty(value = "longitude")
    private String longitude;
	
	@JsonProperty(value = "confirmed_at")
    private String confirmedAt;
	
	@JsonProperty(value = "created_at")
    private String createdAt;
	
	@JsonProperty(value = "updated_at")
    private String updatedAt;
	
	@JsonProperty(value = "city")
	private CityDto city;

	public AddressDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(String confirmedAt) {
		this.confirmedAt = confirmedAt;
	}	

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CityDto getCity() {
		return city;
	}

	public void setCity(CityDto city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "AddressDto [id=" + id + "]";
	}
}