package com.loja.virtual.enums;

public enum StatusContaPagar {

	COBRANCA("pagar"),
	VENCIDA("vencida"),
	ABERTA("Aberta"),
	QUITADA("Quitada"),
	NEGOCIADA("renegociada");
	
	private String descricao;

	private StatusContaPagar(String descricao) {
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
