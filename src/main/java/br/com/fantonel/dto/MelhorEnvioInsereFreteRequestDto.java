package br.com.fantonel.dto;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MelhorEnvioInsereFreteRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "service")
	private int service;
	
	@JsonProperty(value = "agency")
	private Integer agency;
	
	@JsonProperty(value = "from")
	private InsereFreteFromDto from;
	
	@JsonProperty(value = "to")
	private InsereFreteToDto to;	
	
	@JsonProperty(value = "products")
	private InsereFreteProductsDto[] products;
	
	@JsonProperty(value = "volumes")
	private VolumesDto[] volumes;
	
	@JsonProperty(value = "options")
	private InsereFreteOptionsDto options;

	public MelhorEnvioInsereFreteRequestDto() {
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public Integer getAgency() {
		return agency;
	}

	public void setAgency(Integer agency) {
		this.agency = agency;
	}

	public InsereFreteFromDto getFrom() {
		return from;
	}

	public void setFrom(InsereFreteFromDto from) {
		this.from = from;
	}

	public InsereFreteToDto getTo() {
		return to;
	}

	public void setTo(InsereFreteToDto to) {
		this.to = to;
	}

	public InsereFreteProductsDto[] getProducts() {
		return products;
	}

	public void setProducts(InsereFreteProductsDto[] products) {
		this.products = products;
	}	
	
	public VolumesDto[] getVolumes() {
		return volumes;
	}

	public void setVolumes(VolumesDto[] volumes) {
		this.volumes = volumes;
	}

	public InsereFreteOptionsDto getOptions() {
		return options;
	}

	public void setOptions(InsereFreteOptionsDto options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "MelhorEnvioInsereFreteRequestDto [service=" + service + ", agency=" + agency + ", from=" + from
				+ ", to=" + to + ", products=" + Arrays.toString(products) + ", volumes=" + Arrays.toString(volumes)
				+ ", options=" + options + "]";
	}
}