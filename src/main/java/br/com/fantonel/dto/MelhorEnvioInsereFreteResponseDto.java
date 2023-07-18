package br.com.fantonel.dto;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MelhorEnvioInsereFreteResponseDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
	private String id;
	
	@JsonProperty(value = "protocol")
	private String protocol;
	
	@JsonProperty(value = "service_id")
	private String serviceId;
	
	@JsonProperty(value = "agency_id")
	private String agencyId;
	
	@JsonProperty(value = "contract")
	private String contract;
	
	@JsonProperty(value = "service_code")
	private String serviceCode;
	
	@JsonProperty(value = "quote")
	private String quote;
	
	@JsonProperty(value = "price")
	private String price;
	
	@JsonProperty(value = "coupon")
	private String coupon;
	
	@JsonProperty(value = "discount")
	private String discount;
	
	@JsonProperty(value = "delivery_min")
	private String deliveryMin;
	
	@JsonProperty(value = "delivery_max")
	private String deliveryMax;
	
	@JsonProperty(value = "status")
	private String status;
	
	@JsonProperty(value = "reminder")
	private String reminder;
	
	@JsonProperty(value = "insurance_value")
	private String insuranceValue;
	
	@JsonProperty(value = "weight")
	private String weight;
	
	@JsonProperty(value = "width")
	private String width;
	
	@JsonProperty(value = "height")
	private String height;
	
	@JsonProperty(value = "length")
	private String length;
	
	@JsonProperty(value = "diameter")
	private String diameter;
	
	@JsonProperty(value = "format")
	private String format;
	
	@JsonProperty(value = "billed_weight")
	private String billedWeight;
	
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
	private String authorizationcode;
	
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
	
	@JsonProperty(value = "products")
	private InsereFreteProductsDto[] products;
	
	@JsonProperty(value = "volumes")
	private VolumesDto[] volumes;
	
	@JsonProperty(value = "tags")
	private TagsDto[] tags;

	public MelhorEnvioInsereFreteResponseDto() {
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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
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

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getDeliveryMin() {
		return deliveryMin;
	}

	public void setDeliveryMin(String deliveryMin) {
		this.deliveryMin = deliveryMin;
	}

	public String getDeliveryMax() {
		return deliveryMax;
	}

	public void setDeliveryMax(String deliveryMax) {
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

	public String getInsuranceValue() {
		return insuranceValue;
	}

	public void setInsuranceValue(String insuranceValue) {
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

	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getBilledWeight() {
		return billedWeight;
	}

	public void setBilledWeight(String billedWeight) {
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

	public String getAuthorizationcode() {
		return authorizationcode;
	}

	public void setAuthorizationcode(String authorizationcode) {
		this.authorizationcode = authorizationcode;
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

	public InsereFreteProductsDto[] getProducts() {
		return products;
	}

	public void setProducts(InsereFreteProductsDto[] products) {
		this.products = products;
	}

	public VolumesDto[] getVolumes() {
		return volumes;
	}

	public void setVolumes(VolumesDto[] volumes) {
		this.volumes = volumes;
	}

	public TagsDto[] getTags() {
		return tags;
	}

	public void setTags(TagsDto[] tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "MelhorEnvioInsereFreteResponseDto [id=" + id + ", protocol=" + protocol + ", serviceId=" + serviceId
				+ ", agencyId=" + agencyId + ", contract=" + contract + ", serviceCode=" + serviceCode + ", quote="
				+ quote + ", price=" + price + ", coupon=" + coupon + ", discount=" + discount + ", deliveryMin="
				+ deliveryMin + ", deliveryMax=" + deliveryMax + ", status=" + status + ", reminder=" + reminder
				+ ", insuranceValue=" + insuranceValue + ", weight=" + weight + ", width=" + width + ", height="
				+ height + ", length=" + length + ", diameter=" + diameter + ", format=" + format + ", billedWeight="
				+ billedWeight + ", receipt=" + receipt + ", ownHand=" + ownHand + ", collect=" + collect
				+ ", collectScheduledAt=" + collectScheduledAt + ", reverse=" + reverse + ", nonCommercial="
				+ nonCommercial + ", authorizationcode=" + authorizationcode + ", tracking=" + tracking
				+ ", selfTracking=" + selfTracking + ", deliveryReceipt=" + deliveryReceipt + ", additionalInfo="
				+ additionalInfo + ", cteKey=" + cteKey + ", paidAt=" + paidAt + ", generatedAt=" + generatedAt
				+ ", postedAt=" + postedAt + ", deliveredAt=" + deliveredAt + ", canceledAt=" + canceledAt
				+ ", suspendedAt=" + suspendedAt + ", expiredAt=" + expiredAt + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", parsePiAt=" + parsePiAt + ", products=" + Arrays.toString(products)
				+ ", volumes=" + Arrays.toString(volumes) + ", tags=" + Arrays.toString(tags) + "]";
	}
}