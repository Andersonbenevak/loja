package com.loja.virtual.enums;

public enum StatusContaReceber {

	COBRANCA("pagar"),
	VENCIDA("vencida"),
	ABERTA("Aberta"),
	QUITADA("Quitada");
	
	private String descricao;

	private StatusContaReceber(String descricao) {
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
