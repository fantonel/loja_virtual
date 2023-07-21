package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
	private Integer id;
	
	@JsonProperty(value = "name")
    private String name;
	
	@JsonProperty(value = "status")
    private String status;
	
	@JsonProperty(value = "type")
    private String type;
	
	@JsonProperty(value = "range")
    private String range;
	
	@JsonProperty(value = "restrictions")
    private String restrictions;
	
	@JsonProperty(value = "requirements")
	private String requirements;
	
	@JsonProperty(value = "optionals")	
	private String optionals;
	
	@JsonProperty(value = "company")	
	private CompanyDto company;

	public ServiceDto() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getOptionals() {
		return optionals;
	}

	public void setOptionals(String optionals) {
		this.optionals = optionals;
	}

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "ServiceDto [id=" + id + "]";
	}
}