package br.com.fantonel.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrdersDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
    private String id;
	
	@JsonProperty(value = "protocol")
    private String protocol;
	
	@JsonProperty(value = "service_id")
    private Integer serviceId;
	
	@JsonProperty(value = "agency_id")
    private Integer agencyId;
	
	@JsonProperty(value = "contract")
    private String contract;
	
	@JsonProperty(value = "service_code")
    private String serviceCode;
	
	@JsonProperty(value = "quote")
    private BigDecimal quote;
	
	@JsonProperty(value = "price")
    private BigDecimal price;
	
	@JsonProperty(value = "coupon")
    private String coupon;
	
	@JsonProperty(value = "discount")
    private BigDecimal discount;
	
	@JsonProperty(value = "delivery_min")
    private Integer deliveryMin;
	
	@JsonProperty(value = "delivery_max")
    private Integer deliveryMax;
	
	@JsonProperty(value = "status")
    private String status;
	
	@JsonProperty(value = "reminder")
    private String reminder;
	
	@JsonProperty(value = "insurance_value")
    private Integer insuranceValue;
	
	@JsonProperty(value = "weight")
    private String weight;
	
	@JsonProperty(value = "width")
    private String width;
	
	@JsonProperty(value = "height")
    private String height;
	
	@JsonProperty(value = "length")
    private String length;
	
	@JsonProperty(value = "diameter")
    private BigDecimal diameter;
	
	@JsonProperty(value = "format")
    private String format;
	
	@JsonProperty(value = "billed_weight")
    private BigDecimal billedWeight;
	
	@JsonProperty(value = "receipt")
    private boolean receipt;
	
	@JsonProperty(value = "own_hand")
    private boolean ownHand;
	
	@JsonProperty(value = "collect")
    private boolean collect;
	
	@JsonProperty(value = "collect_scheduled_at")
    private String collectScheduledAt;
	
	@JsonProperty(value = "reverse")
    private boolean reverse;
	
	@JsonProperty(value = "non_commercial")
    private boolean nonCommercial;
	
	@JsonProperty(value = "authorization_code")
    private String authorizationCode;
	
	@JsonProperty(value = "tracking")
    private String tracking;
	
	@JsonProperty(value = "self_tracking")
    private String selfTracking;
	
	@JsonProperty(value = "delivery_receipt")
    private String deliveryReceipt;
	
	@JsonProperty(value = "additional_info")
    private String additionalInfo;
	
	@JsonProperty(value = "cte_key")
    private String cteKey;
	
	@JsonProperty(value = "paid_at")
    private String paidAt;
	
	@JsonProperty(value = "generated_at")
    private String generatedAt;
	
	@JsonProperty(value = "posted_at")
    private String postedAt;
	
	@JsonProperty(value = "delivered_at")
    private String deliveredAt;
	
	@JsonProperty(value = "canceled_at")
    private String canceledAt;
	
	@JsonProperty(value = "suspended_at")
    private String suspendedAt;
	
	@JsonProperty(value = "expired_at")
    private String expiredAt;
	
	@JsonProperty(value = "created_at")
    private String createdAt;
	
	@JsonProperty(value = "updated_at")
    private String updatedAt;
	
	@JsonProperty(value = "parse_pi_at")
    private String parsePiAt;
    
	@JsonProperty(value = "from")
    private PurchaseFromDto from;
    
	@JsonProperty(value = "to")
    private PurchaseToDto to;
    
	@JsonProperty(value = "service")
    private ServiceDto service;
    
	@JsonProperty(value = "agency")
    private AgencyDto agencyDto;
    
	@JsonProperty(value = "invoice")
    private InvoiceDto invoice;
    
	@JsonProperty(value = "tags")
    private OrderTagsDto[] tags;
    
	@JsonProperty(value = "products")
    private ProductsDto[] products;
    
	@JsonProperty(value = "generated_key")
    private String generated_key;

	public OrdersDto() {
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

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public BigDecimal getQuote() {
		return quote;
	}

	public void setQuote(BigDecimal quote) {
		this.quote = quote;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getDeliveryMin() {
		return deliveryMin;
	}

	public void setDeliveryMin(Integer deliveryMin) {
		this.deliveryMin = deliveryMin;
	}

	public Integer getDeliveryMax() {
		return deliveryMax;
	}

	public void setDeliveryMax(Integer deliveryMax) {
		this.deliveryMax = deliveryMax;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public Integer getInsuranceValue() {
		return insuranceValue;
	}

	public void setInsuranceValue(Integer insuranceValue) {
		this.insuranceValue = insuranceValue;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public BigDecimal getDiameter() {
		return diameter;
	}

	public void setDiameter(BigDecimal diameter) {
		this.diameter = diameter;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public BigDecimal getBilledWeight() {
		return billedWeight;
	}

	public void setBilledWeight(BigDecimal billedWeight) {
		this.billedWeight = billedWeight;
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

	public boolean isCollect() {
		return collect;
	}

	public void setCollect(boolean collect) {
		this.collect = collect;
	}

	public String getCollectScheduledAt() {
		return collectScheduledAt;
	}

	public void setCollectScheduledAt(String collectScheduledAt) {
		this.collectScheduledAt = collectScheduledAt;
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

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getTracking() {
		return tracking;
	}

	public void setTracking(String tracking) {
		this.tracking = tracking;
	}

	public String getSelfTracking() {
		return selfTracking;
	}

	public void setSelfTracking(String selfTracking) {
		this.selfTracking = selfTracking;
	}

	public String getDeliveryReceipt() {
		return deliveryReceipt;
	}

	public void setDeliveryReceipt(String deliveryReceipt) {
		this.deliveryReceipt = deliveryReceipt;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getCteKey() {
		return cteKey;
	}

	public void setCteKey(String cteKey) {
		this.cteKey = cteKey;
	}

	public String getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(String paidAt) {
		this.paidAt = paidAt;
	}

	public String getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(String generatedAt) {
		this.generatedAt = generatedAt;
	}

	public String getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(String postedAt) {
		this.postedAt = postedAt;
	}

	public String getDeliveredAt() {
		return deliveredAt;
	}

	public void setDeliveredAt(String deliveredAt) {
		this.deliveredAt = deliveredAt;
	}

	public String getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(String canceledAt) {
		this.canceledAt = canceledAt;
	}

	public String getSuspendedAt() {
		return suspendedAt;
	}

	public void setSuspendedAt(String suspendedAt) {
		this.suspendedAt = suspendedAt;
	}

	public String getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(String expiredAt) {
		this.expiredAt = expiredAt;
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

	public String getParsePiAt() {
		return parsePiAt;
	}

	public void setParsePiAt(String parsePiAt) {
		this.parsePiAt = parsePiAt;
	}

	public PurchaseFromDto getFrom() {
		return from;
	}

	public void setFrom(PurchaseFromDto from) {
		this.from = from;
	}

	public PurchaseToDto getTo() {
		return to;
	}

	public void setTo(PurchaseToDto to) {
		this.to = to;
	}

	public ServiceDto getService() {
		return service;
	}

	public void setService(ServiceDto service) {
		this.service = service;
	}

	public AgencyDto getAgencyDto() {
		return agencyDto;
	}

	public void setAgencyDto(AgencyDto agencyDto) {
		this.agencyDto = agencyDto;
	}

	public InvoiceDto getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceDto invoice) {
		this.invoice = invoice;
	}

	public OrderTagsDto[] getTags() {
		return tags;
	}

	public void setTags(OrderTagsDto[] tags) {
		this.tags = tags;
	}

	public ProductsDto[] getProducts() {
		return products;
	}

	public void setProducts(ProductsDto[] products) {
		this.products = products;
	}

	public String getGenerated_key() {
		return generated_key;
	}

	public void setGenerated_key(String generated_key) {
		this.generated_key = generated_key;
	}

	@Override
	public String toString() {
		return "OrdersDto [id=" + id + "]";
	}
}