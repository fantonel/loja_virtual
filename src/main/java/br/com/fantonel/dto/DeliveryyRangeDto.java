package br.com.fantonel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryyRangeDto {
	@JsonProperty(value = "min")
	private String min;
	@JsonProperty(value = "max")
	private String max;
	
	public DeliveryyRangeDto() {}

	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}	
}