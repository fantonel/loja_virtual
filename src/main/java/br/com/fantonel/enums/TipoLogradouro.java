package br.com.fantonel.enums;

public enum TipoLogradouro {
	RUA("Rua"),
	AVENIDA("Avenida"),
	AEROPORTO("Aeroporto"),
	ALAMEDA("Alameda"),
	AREA("Área"),
	CAMPO("Campo"),
	CHACARA("Chácara"),
	COLONIA("Colônia"),
	CONDOMINIO("Condomínio"),
	CONJUNTO("Conjunto"),
	DISTRITO("Distrito"),
	ESPLANADA("Esplanada"),
	ESTACAO("Estação"),
	ESTRADA("Estrada"),
	FAVELA("Favela"),
	FAZENDA("Fazenda"),
	FEIRA("Feira"),
	JARDIM("Jardim"),
	LADEIRA("Ladeira"),
	LAGO("Lago"),
	LAGOA("Lagoa"),
	LARGO("Largo"),
	LOTEAMENTO("Loteamento"),
	MORRO("Morro"),
	NUCLEO("Núcleo"),
	PARQUE("Parque"),
	PASSARELA("Passarela"),
	PATIO("Pátio"),
	PRACA("Praça"),
	QUADRA("Quadra"),
	RECANDO("Recanto"),
	RESIDENCIAL("Residencial"),
	RODOVIA("Rodovia"),
	SETOR("Setor"),
	SITIO("Sítio"),
	TRAVESSA("Travessa"),
	TRECHO("Trecho"),
	TREVO("Trevo"),
	VALE("Vale"),
	VEREDA("Vereda"),
	VIA("Via"),
	VIADUTO("Viaduto"),
	VIELA("viela"),
	VILA("Vila");
	
	private String descricao;

	TipoLogradouro(String descricao) {
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