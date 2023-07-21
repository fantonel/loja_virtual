package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
	private String id;
	
	@JsonProperty(value = "label")
	private String label;
	
	@JsonProperty(value = "phone")
    private String phone;
	
	@JsonProperty(value = "type")
    private String type;
	
	@JsonProperty(value = "country_id")
    private String country_id;
	
	@JsonProperty(value = "confirmed_at")
    private String confirmed_at;
	
	@JsonProperty(value = "created_at")
    private String created_at;
	
	@JsonProperty(value = "updated_at")
    private String updated_at;

	public PhoneDto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getConfirmed_at() {
		return confirmed_at;
	}

	public void setConfirmed_at(String confirmed_at) {
		this.confirmed_at = confirmed_at;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "PhoneDto [id=" + id + ", label=" + label + ", phone=" + phone + ", type=" + type + ", country_id="
				+ country_id + ", confirmed_at=" + confirmed_at + ", created_at=" + created_at + ", updated_at="
				+ updated_at + ", getId()=" + getId() + ", getLabel()=" + getLabel() + ", getPhone()=" + getPhone()
				+ ", getType()=" + getType() + ", getCountry_id()=" + getCountry_id() + ", getConfirmed_at()="
				+ getConfirmed_at() + ", getCreated_at()=" + getCreated_at() + ", getUpdated_at()=" + getUpdated_at()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}