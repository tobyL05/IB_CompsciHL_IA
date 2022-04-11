package com.ibcompsci_ia.Enums;

public enum fxmlStyles {
	transp_button("-fx-background-color: transparent"),;

	private final String text;

	fxmlStyles(String text){
		this.text = text;
	}

	@Override
	public String toString(){
		return text;
	}
}
