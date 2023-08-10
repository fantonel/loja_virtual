package br.com.fantonel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CancelOrderDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Orders orders;
	
	public CancelOrderDto() {
	}
	
	public CancelOrderDto(Orders orders) {
		super();
		this.orders = orders;
	}
	
	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public class Orders {
		@JsonProperty(value = "id")
		private String id;
		
		@JsonProperty(value = "reason_id")
		private String reasonId;
		
		@JsonProperty(value = "description")
		private String description;
		
		public Orders(){}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getReasonId() {
			return reasonId;
		}

		public void setReasonId(String reasonId) {
			this.reasonId = reasonId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}		
	}
}
