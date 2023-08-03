package br.com.fantonel.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseOrdersDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
    private String id;
	
	@JsonProperty(value = "protocol")
    private String protocol;
	
	@JsonProperty(value = "total")
    private BigDecimal total;
	
	@JsonProperty(value = "discount")
    private BigDecimal discount;
	
	@JsonProperty(value = "status")
    private String status;
	
	@JsonProperty(value = "paid_at")
    private String paidAt;
	
	@JsonProperty(value = "canceled_at")
    private String canceledAt;
	
	@JsonProperty(value = "created_at")
    private String createdAt;
	
	@JsonProperty(value = "updated_at")
    private String updatedAt;
	
	@JsonProperty(value = "payment")
    private String payment;
    
    @JsonProperty(value = "transactions")
    private TransactionsDto[] transactions;
        
    @JsonProperty(value = "orders")
    private OrdersDto[] orders;
    
    @JsonProperty(value = "paypal_discounts")
    private PaypalDiscountsDto[] paypalDiscounts;

	public PurchaseOrdersDto() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(String paidAt) {
		this.paidAt = paidAt;
	}

	public String getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(String canceledAt) {
		this.canceledAt = canceledAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public TransactionsDto[] getTransactions() {
		return transactions;
	}

	public void setTransactions(TransactionsDto[] transactions) {
		this.transactions = transactions;
	}

	public OrdersDto[] getOrders() {
		return orders;
	}

	public void setOrders(OrdersDto[] orders) {
		this.orders = orders;
	}

	public PaypalDiscountsDto[] getPaypalDiscounts() {
		return paypalDiscounts;
	}

	public void setPaypalDiscounts(PaypalDiscountsDto[] paypalDiscounts) {
		this.paypalDiscounts = paypalDiscounts;
	}

	@Override
	public String toString() {
		return "PurchaseOrdersDto [id=" + id + "]";
	}
}