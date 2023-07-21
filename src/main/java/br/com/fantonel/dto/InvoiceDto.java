package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "key")
	private String key;
	
	@JsonProperty(value = "model")
	private Integer model;
	
	@JsonProperty(value = "number")
	private Integer number;
	
	@JsonProperty(value = "serie")
	private Integer serie;
	
	@JsonProperty(value = "value")
    private String value;
	
	@JsonProperty(value = "cfop")
    private String cfop;
	
	@JsonProperty(value = "issued_at")
    private String issued_at;
	
	@JsonProperty(value = "uploaded_at")
    private String uploaded_at;
	
	@JsonProperty(value = "to_document")
    private String to_document;

	public InvoiceDto() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public String getIssued_at() {
		return issued_at;
	}

	public void setIssued_at(String issued_at) {
		this.issued_at = issued_at;
	}

	public String getUploaded_at() {
		return uploaded_at;
	}

	public void setUploaded_at(String uploaded_at) {
		this.uploaded_at = uploaded_at;
	}

	public String getTo_document() {
		return to_document;
	}

	public void setTo_document(String to_document) {
		this.to_document = to_document;
	}

	@Override
	public String toString() {
		return "InvoiceDto [key=" + key + "]";
	}
}