package br.com.fantonel.enums;

public enum TipoEndereco {	
	COBRANCA("Cobrança"),
	ENTREGA("Entrega"),
	ENVIO("Envio");
	
	private String descricao;

	TipoEndereco(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}
}