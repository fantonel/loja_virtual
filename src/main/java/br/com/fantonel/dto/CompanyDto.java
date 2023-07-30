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
	
	@JsonProperty(value = "has_grouped_volumes")
	private boolean hasGroupedVolumes;
	
	@JsonProperty(value = "tracking_link")
	private String trackingLink;
	
	@JsonProperty(value = "batch_size")
	private int batchSize;

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

	public boolean isHasGroupedVolumes() {
		return hasGroupedVolumes;
	}

	public void setHasGroupedVolumes(boolean hasGroupedVolumes) {
		this.hasGroupedVolumes = hasGroupedVolumes;
	}

	public String getTrackingLink() {
		return trackingLink;
	}

	public void setTrackingLink(String trackingLink) {
		this.trackingLink = trackingLink;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	@Override
	public String toString() {
		return "CompanyDto [id=" + id + "]";
	}
}