package com.ibcompsci_ia.Enums;

public enum fxmlStyles {
	transp_button("-fx-background-color: transparent"),
	tooltip_style("-fx-background: rgba(30,30,30);-fx-text-fill: white;-fx-background-color: rgba(30,30,30,0.8);-fx-background-radius: 6px;-fx-background-insets: 0;-fx-padding: 0.667em 0.75em 0.667em 0.75em; /* 10px */-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.5) , 10, 0.0 , 0 , 3 );-fx-font-size: 0.85em;");

	private final String text;

	fxmlStyles(String text){
		this.text = text;
	}

	@Override
	public String toString(){
		return text;
	}
}
