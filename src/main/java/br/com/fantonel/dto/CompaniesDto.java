package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompaniesDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
    private int id;
	
	@JsonProperty(value = "name")
    private String name;
	
	@JsonProperty(value = "has_grouped_volumes")
    private int hasGroupedVolumes;
	
	@JsonProperty(value = "status")
    private String status;
	
	@JsonProperty(value = "picture")
    private String picture;
	
	@JsonProperty(value = "tracking_link")
    private String trackingLink;
	
	@JsonProperty(value = "use_own_contract")
    private boolean useOwnContract;
	
	@JsonProperty(value = "batch_size")
    private int batchSize;
	
	@JsonProperty(value = "pivot")
	private PivotDto pivot;

	public CompaniesDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHasGroupedVolumes() {
		return hasGroupedVolumes;
	}

	public void setHasGroupedVolumes(int hasGroupedVolumes) {
		this.hasGroupedVolumes = hasGroupedVolumes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTrackingLink() {
		return trackingLink;
	}

	public void setTrackingLink(String trackingLink) {
		this.trackingLink = trackingLink;
	}

	public boolean isUseOwnContract() {
		return useOwnContract;
	}

	public void setUseOwnContract(boolean useOwnContract) {
		this.useOwnContract = useOwnContract;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public PivotDto getPivot() {
		return pivot;
	}

	public void setPivot(PivotDto pivot) {
		this.pivot = pivot;
	}

	@Override
	public String toString() {
		return "Companies [id=" + id + "]";
	}
}