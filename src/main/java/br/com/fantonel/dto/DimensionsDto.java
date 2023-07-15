package br.com.fantonel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DimensionsDto {
	@JsonProperty(value = "height")
	private String height;
	@JsonProperty(value = "width")
	private String width;
	@JsonProperty(value = "length")
	private String length;
	
	public DimensionsDto() {}		
	
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}		
}