package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderTagsDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "tag")
	private String tag;
	
	@JsonProperty(value = "url")
	private String url;

	public OrderTagsDto() {
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "OrderTagsDto [tag=" + tag + ", url=" + url + "]";
	}
}