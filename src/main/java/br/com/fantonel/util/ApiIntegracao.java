package br.com.fantonel.util;

public class ApiIntegracao {
	public static final String MELHORENVIO_SANDBOX_TOKKEN = "Bearer [Seu Token]";
	//	
	public static final String MELHORENVIO_SANDBOX_URL_AUTH = "https://sandbox.melhorenvio.com.br/oauth/token";
	//Cálculo / Opções disponíveis de frete
	public static final String MELHORENVIO_SANDBOX_URL_CALCULATE = "https://sandbox.melhorenvio.com.br/api/v2/me/shipment/calculate";
	//Inseir Frete
	public static final String MELHORENVIO_SANDBOX_URL_CART = "https://sandbox.melhorenvio.com.br/api/v2/me/cart";
	//Comprar Frete
	public static final String MELHORENVIO_SANDBOX_URL_CHECKOUT = "https://sandbox.melhorenvio.com.br/api/v2/me/shipment/checkout";
	//Etiqueta - Geração
	public static final String MELHORENVIO_SANDBOX_URL_TAG = "https://sandbox.melhorenvio.com.br/api/v2/me/shipment/generate";
	//Etiqueta - Impressão
	public static final String MELHORENVIO_SANDBOX_URL_TAG_PRINT = "https://sandbox.melhorenvio.com.br/api/v2/me/shipment/print";
	//Etiqueta - Visualização
	public static final String MELHORENVIO_SANDBOX_URL_TAG_PREVIEW = "https://sandbox.melhorenvio.com.br/api/v2/me/shipment/preview";
	//Etiqueta - Verificar se pode ser cancelada.
	public static final String  MELHORENVIO_SANDBOX_URL_TAG_CANCELLABLE = "https://sandbox.melhorenvio.com.br/api/v2/me/shipment/cancellable";
	//Etiqueta - Cancelamento
	public static final String  MELHORENVIO_SANDBOX_URL_TAG_CANCEL = "https://sandbox.melhorenvio.com.br/api/v2/me/shipment/cancel";	
	
	//Consultar transportadora no melhor envio.
	public static final String MELHORENVIO_SANDBOX_COMPANIES = "https://sandbox.melhorenvio.com.br/api/v2/me/shipment/companies";

	public ApiIntegracao() {
	}
}