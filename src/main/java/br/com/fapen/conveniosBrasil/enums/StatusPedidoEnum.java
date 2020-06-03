package br.com.fapen.conveniosBrasil.enums;

public enum StatusPedidoEnum {
	EM_DIGITACAO("Em digitação"), RECEBIDO("Recebido"), CANCELADO("Cancelado");
	
	private final String displayValue;

	private StatusPedidoEnum(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
}
