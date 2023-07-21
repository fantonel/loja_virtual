package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyDto implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "id")
	private Integer id;
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "picture")
	private String picture;
	
	@JsonProperty(value = "status")
	private String status;
	
	@JsonProperty(value = "use_own_contract")
	private boolean useOwnContract;

	public CompanyDto() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isUseOwnContract() {
		return useOwnContract;
	}

	public void setUseOwnContract(boolean useOwnContract) {
		this.useOwnContract = useOwnContract;
	}

	@Override
	public String toString() {
		return "CompanyDto [id=" + id + "]";
	}
}