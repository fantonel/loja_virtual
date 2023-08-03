package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PivotDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "agency_id")
	private int agencyId;
	
	@JsonProperty(value = "company_id")
    private int companyId;

	public PivotDto() {
	}

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "PivotDto [agencyId=" + agencyId + ", companyId=" + companyId + "]";
	}
}