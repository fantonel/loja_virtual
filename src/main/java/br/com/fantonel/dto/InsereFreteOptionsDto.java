package br.com.fantonel.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsereFreteOptionsDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "insurance_value")
    private BigDecimal insuranceValue;
	
	@JsonProperty(value = "receipt")
    private boolean receipt;
	
	@JsonProperty(value = "own_hand")
    private boolean ownHand;
	
	@JsonProperty(value = "reverse")
    private boolean reverse;
	
	@JsonProperty(value = "non_commercial")
    private boolean nonCommercial;
	
	@JsonProperty(value = "invoice")
	private InvoiceDto invoice;
	
	@JsonProperty(value = "plataform")
	private String plataform;
	
	@JsonProperty(value = "tags")
	private TagsDto[] tags;

	public InsereFreteOptionsDto() {
	}

	public BigDecimal getInsuranceValue() {
		return insuranceValue;
	}

	public void setInsuranceValue(BigDecimal insuranceValue) {
		this.insuranceValue = insuranceValue;
	}

	public boolean isReceipt() {
		return receipt;
	}

	public void setReceipt(boolean receipt) {
		this.receipt = receipt;
	}

	public boolean isOwnHand() {
		return ownHand;
	}

	public void setOwnHand(boolean ownHand) {
		this.ownHand = ownHand;
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public boolean isNonCommercial() {
		return nonCommercial;
	}

	public void setNonCommercial(boolean nonCommercial) {
		this.nonCommercial = nonCommercial;
	}

	public InvoiceDto getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceDto invoice) {
		this.invoice = invoice;
	}

	public String getPlataform() {
		return plataform;
	}

	public void setPlataform(String plataform) {
		this.plataform = plataform;
	}

	public TagsDto[] getTags() {
		return tags;
	}

	public void setTags(TagsDto[] tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "InsereFreteOptionsDto [insuranceValue=" + insuranceValue + ", receipt=" + receipt + ", ownHand="
				+ ownHand + ", reverse=" + reverse + ", nonCommercial=" + nonCommercial + ", invoice=" + invoice
				+ ", plataform=" + plataform + ", tags=" + Arrays.toString(tags) + "]";
	}	
}