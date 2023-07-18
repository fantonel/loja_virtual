package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsereFreteToDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "name")
    private String name;
	
	@JsonProperty(value = "phone")
    private String phone;
	
	@JsonProperty(value = "email")
    private String email;
	
	@JsonProperty(value = "document")
    private String document;
	
	@JsonProperty(value = "company_document")
    private String companyDocument;
	
	@JsonProperty(value = "state_register")
    private String stateRegister;
	
	@JsonProperty(value = "address")
    private String address;
	
	@JsonProperty(value = "complement")
    private String complement;
	
	@JsonProperty(value = "number")
    private String number;
	
	@JsonProperty(value = "district")
    private String district;
	
	@JsonProperty(value = "city")
    private String city;
	
	@JsonProperty(value = "state_abbr")
    private String stateAbbr;	
	
	@JsonProperty(value = "country_id")
    private String countryId;
	
	@JsonProperty(value = "postal_code")
    private String postalCode;
	
	@JsonProperty(value = "note")
    private String note;

	public InsereFreteToDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getCompanyDocument() {
		return companyDocument;
	}

	public void setCompanyDocument(String companyDocument) {
		this.companyDocument = companyDocument;
	}

	public String getStateRegister() {
		return stateRegister;
	}

	public void setStateRegister(String stateRegister) {
		this.stateRegister = stateRegister;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateAbbr() {
		return stateAbbr;
	}

	public void setStateAbbr(String stateAbbr) {
		this.stateAbbr = stateAbbr;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "InsereFreteToDto [document=" + document + ", companyDocument=" + companyDocument + "]";
	}
}