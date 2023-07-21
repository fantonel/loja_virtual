package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgencyDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
    private Integer id;
	
	@JsonProperty(value = "company_id")
    private Integer companyId;
	
	@JsonProperty(value = "name")
    private String name;
	
	@JsonProperty(value = "initials")
    private String initials;
	
	@JsonProperty(value = "code")
    private String code;
	
	@JsonProperty(value = "status")
    private String status;
	
	@JsonProperty(value = "company_name")
    private String companyName;
	
	@JsonProperty(value = "email")
    private String email;
	
	@JsonProperty(value = "note")
    private String note;
	
	@JsonProperty(value = "created_at")
    private String createdAt;
	
	@JsonProperty(value = "updated_at")
    private String updatedAt;
	
	@JsonProperty(value = "address")
	private AddressDto address;
	
	@JsonProperty(value = "phone")
	private PhoneDto phone;

	public AgencyDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public PhoneDto getPhone() {
		return phone;
	}

	public void setPhone(PhoneDto phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "AgencyDto [id=" + id + "]";
	}
}