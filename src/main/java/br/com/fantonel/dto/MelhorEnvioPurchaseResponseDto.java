package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MelhorEnvioPurchaseResponseDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "purchase")
	private PurchaseOrdersDto purchase;
	
    @JsonProperty(value = "digitable")
    private String digitable;
    
    @JsonProperty(value = "redirect")
    private String redirect;
    
    @JsonProperty(value = "message")
    private String message;
    
    @JsonProperty(value = "token")
    private String token;
    
    @JsonProperty(value = "payment_id")
    private String payment_id;

	public MelhorEnvioPurchaseResponseDto() {
	}

	public PurchaseOrdersDto getPurchase() {
		return purchase;
	}

	public void setPurchase(PurchaseOrdersDto purchase) {
		this.purchase = purchase;
	}
	
	public String getDigitable() {
		return digitable;
	}

	public void setDigitable(String digitable) {
		this.digitable = digitable;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	@Override
	public String toString() {
		return "MelhorEnvioPurchaseResponseDto [purchase=" + purchase + "]";
	}	
}