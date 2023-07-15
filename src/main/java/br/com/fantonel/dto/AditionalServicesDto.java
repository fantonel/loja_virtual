package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AditionalServicesDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "receipt")
	private String receipt;
	
	@JsonProperty(value = "own_hand")
	private String ownHand;
	
	@JsonProperty(value = "collect")
	private String collect;

	public AditionalServicesDto() {
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getOwnHand() {
		return ownHand;
	}

	public void setOwnHand(String ownHand) {
		this.ownHand = ownHand;
	}

	public String getCollect() {
		return collect;
	}

	public void setCollect(String collect) {
		this.collect = collect;
	}

	@Override
	public String toString() {
		return "AditionalServicesDto [receipt=" + receipt + ", ownHand=" + ownHand + ", collect=" + collect + "]";
	}
}