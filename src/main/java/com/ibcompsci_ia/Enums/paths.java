package com.ibcompsci_ia.Enums;

public enum paths {
	accountsPath("/com/ibcompsci_ia/Accounts/");
	
	private final String text;

	paths(String text){
		this.text = text;
	}

	@Override
	public String toString(){
		return text;
	}
}
