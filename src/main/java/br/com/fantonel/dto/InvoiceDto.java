package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "key")
	private String key;

	public InvoiceDto() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "InvoiceDto [key=" + key + "]";
	}
}