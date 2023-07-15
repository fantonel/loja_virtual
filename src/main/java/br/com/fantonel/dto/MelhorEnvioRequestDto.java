package br.com.fantonel.dto;

import java.util.List;

public class MelhorEnvioRequestDto {
	
	private ConsultaFreteOrigemDto  from;
	private ConsultaFreteDestinoDto to;
	
	private List<ConsultaFreteProductsDto> products;

	public MelhorEnvioRequestDto() {
	}

	public ConsultaFreteOrigemDto getFrom() {
		return from;
	}

	public void setFrom(ConsultaFreteOrigemDto from) {
		this.from = from;
	}

	public ConsultaFreteDestinoDto getTo() {
		return to;
	}

	public void setTo(ConsultaFreteDestinoDto to) {
		this.to = to;
	}

	public List<ConsultaFreteProductsDto> getProducts() {
		return products;
	}

	public void setProducts(List<ConsultaFreteProductsDto> products) {
		this.products = products;
	}	
}