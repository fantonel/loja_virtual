package br.com.fantonel.dto;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MelhorEnvioCompraFreteOrdersDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "orders")
	private CompraFreteOrdersDto[] orders;

	public MelhorEnvioCompraFreteOrdersDto() {
	}

	public CompraFreteOrdersDto[] getOrders() {
		return orders;
	}

	public void setOrders(CompraFreteOrdersDto[] orders) {
		this.orders = orders;
	}
	
	public void setOrder(String[] ordersId) {
		if (ordersId != null && ordersId.length > 0) {
			orders = new CompraFreteOrdersDto[ordersId.length];
			int position = 0;
			for (String orderId : ordersId) {
				orders[position] = new CompraFreteOrdersDto(orderId);
				position++;				
			}			
		}
	}

	@Override
	public String toString() {
		return "MelhorEnvioCompraFreteOrdersDto [orders=" + Arrays.toString(orders) + "]";
	}
}