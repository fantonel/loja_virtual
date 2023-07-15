package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyDto implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "id")
	private int id;
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "picture")
	private String picture;

	public CompanyDto() {
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "CompanyDto [id=" + id + ", name=" + name + ", picture=" + picture + "]";
	}
}